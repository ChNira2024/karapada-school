package com.karapada.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.karapada.school.dto.NotificationRequest;
import com.karapada.school.entity.Student;
import com.karapada.school.entity.academic.Attendance;
import com.karapada.school.entity.academic.Marks;
import com.karapada.school.repository.AttendanceRepository;
import com.karapada.school.repository.MarksRepository;
import com.karapada.school.repository.StudentRepository;
import com.karapada.school.util.PdfGenerator;

@Service
public class ReportService {

    @Autowired 
    private StudentRepository studentRepo;
    
    @Autowired 
    private AttendanceRepository attendanceRepo;
    
    @Autowired 
    private MarksRepository marksRepo;
    
    @Autowired 
    private NotificationService notificationService;
    
    private final WebClient webClient;

    public ReportService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:2425") // Notification Service
                .build();
    }


    @Async
    public void generateReportAsync(Long studentId) {

        try {
            System.out.println("Starting report generation for studentId: " + studentId);

            // 1️⃣ Fetch student
            Student student = studentRepo.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            // 2️⃣ Fetch attendance
            List<Attendance> attendanceList =
                    attendanceRepo.findByStudentId(studentId);

            // 3️⃣ Fetch marks
            List<Marks> marksList =
                    marksRepo.findByStudentId(studentId);

            // 4️⃣ Generate PDF
            String pdfPath = PdfGenerator.generate(student, attendanceList, marksList);

            System.out.println("PDF generated at: " + pdfPath);

            // 5️⃣ Prepare Notification Request
            NotificationRequest request = new NotificationRequest();
            request.setTo(student.getEmail());
            request.setSubject("Student Report Generated");
            request.setMessage(
                    "Hello " + student.getFullName() +
                    ", your report is ready.\nFile: " + pdfPath
            );

            // 6️⃣ Call Notification Microservice
            webClient.post()
                    .uri("/notify")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnSuccess(res -> System.out.println("Notification sent: " + res))
                    .doOnError(err -> System.out.println("Notification failed: " + err.getMessage()))
                    .subscribe();

        } catch (Exception e) {
            System.out.println("Error in report generation: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
