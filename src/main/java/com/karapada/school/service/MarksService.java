package com.karapada.school.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karapada.school.dto.MarksRequest;
import com.karapada.school.dto.MarksResponse;
import com.karapada.school.dto.StudentMarksDTO;
import com.karapada.school.entity.Student;
import com.karapada.school.entity.academic.ClassRoom;
import com.karapada.school.entity.academic.Exam;
import com.karapada.school.entity.academic.Marks;
import com.karapada.school.entity.academic.Subject;
import com.karapada.school.repository.ClassRoomRepository;
import com.karapada.school.repository.ExamRepository;
import com.karapada.school.repository.MarksRepository;
import com.karapada.school.repository.StudentRepository;
import com.karapada.school.repository.SubjectRepository;

@Service
public class MarksService {

    @Autowired
    private MarksRepository repo;

    @Autowired private MarksRepository marksRepo;
    @Autowired private StudentRepository studentRepository;
    @Autowired private ClassRoomRepository classRoomRepository;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private ExamRepository examRepository;

    public List<MarksResponse> uploadMarks(MarksRequest request, String teacherUsername) {

        ClassRoom classRoom = classRoomRepository.findById(request.getClassRoomId())
                .orElseThrow(() -> new RuntimeException("ClassRoom not found"));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        List<Marks> marksList = new ArrayList<>();

        for (StudentMarksDTO dto : request.getStudentMarks()) {

            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            Marks marks = new Marks();
            marks.setStudent(student);
            marks.setClassRoom(classRoom);
            marks.setSubject(subject);
            marks.setExam(exam); // ✅ FIXED

            marks.setMarksObtained(dto.getMarks());
            marks.setMaxMarks(request.getMaxMarks());

            marksList.add(marks);
        }

        List<Marks> saved = marksRepo.saveAll(marksList);

        return saved.stream().map(m -> {

            MarksResponse res = new MarksResponse();

            res.setStudentId(m.getStudent().getId());
            res.setStudentName(m.getStudent().getFullName());

            res.setSubjectId(m.getSubject().getId());
            res.setSubjectName(m.getSubject().getSubjectName());

            res.setMarks(m.getMarksObtained()); 
            res.setExamDate(m.getExam().getExamDate()); 

            return res;

        }).toList();
    }
    
    public List<Marks> getStudentMarks(Long studentId) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));

		return marksRepo.findByStudent(student);
	}
    
}
