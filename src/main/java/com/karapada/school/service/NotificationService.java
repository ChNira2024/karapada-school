package com.karapada.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.karapada.school.dto.NotificationRequest;
import com.karapada.school.entity.Student;

@Service
public class NotificationService {

    private final WebClient webClient;

    public NotificationService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:2424") // SAME APP
                .build();
    }

    public void sendReportNotification(Student student, String pdfPath) {

        NotificationRequest request = new NotificationRequest(
                student.getEmail(),                     // ✅ email
                "Student Report Generated",
                "Your report is ready. File path: " + pdfPath
        );

        webClient.post()
                .uri("/notification/send")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(); // async, non-blocking
    }
}
