package com.karapada.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.school.dto.StudentResponse;
import com.karapada.school.dto.TeacherResponse;
import com.karapada.school.entity.Student;
import com.karapada.school.entity.Teacher;
import com.karapada.school.entity.academic.ClassRoom;
import com.karapada.school.entity.academic.Exam;
import com.karapada.school.entity.academic.Subject;
import com.karapada.school.repository.ExamRepository;
import com.karapada.school.service.HeadmasterService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/headmaster")
 @PreAuthorize("hasRole('HEADMASTER')")
public class HeadmasterController {

	private static final Logger logger = LoggerFactory.getLogger(HeadmasterController.class);
    @Autowired
    private HeadmasterService headmasterService;
    @Autowired
    private ExamRepository examRepository;

    @PostMapping("/teacher")
    @Operation(summary="addTeacher() of HeadmasterController class ",description = "Here we add new Teacher")
    public ResponseEntity<TeacherResponse> addTeacher(@RequestBody Teacher teacher) {
    	logger.info("Inside addTecaher(), HeadmasterController");
    	TeacherResponse addedTeacher =  headmasterService.addTeacher(teacher);
        logger.info("Inside addTecaher(), addedTeacher details are {}",addedTeacher);
        return ResponseEntity.ok(addedTeacher);
    }

    @PostMapping("/student")
    @Operation(summary="addStudent() of HeadmasterController class ",description = "Here we add new Student")
    public ResponseEntity<StudentResponse> addStudent(@RequestBody Student student) {
    	logger.info("Inside addStudent(), HeadmasterController");
    	StudentResponse addedStudent =  headmasterService.addStudent(student);
        logger.info("Inside addedStudent(), addedStudent details are {}",addedStudent);
        return ResponseEntity.ok(addedStudent);
    }

    @PostMapping("/classroom")
    @Operation(summary="createClassRoom() of HeadmasterController class ",description = "Here we create Classroom")
    public ResponseEntity<?> createClassRoom(@RequestBody ClassRoom classRoom) {
    	logger.info("Inside createClassRoom(), HeadmasterController");
        ClassRoom createdClass =  headmasterService.createClassRoom(classRoom);
        logger.info("Inside createdClass(), createdClass details are {}",createdClass);
        return ResponseEntity.ok(createdClass);
    }

    @PostMapping("/subject")
    @Operation(summary="createSubject() of HeadmasterController class",description = "Here we create Subject")
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
    	logger.info("Inside createClassRoom(), HeadmasterController");
        Subject createdSubject =  headmasterService.createSubject(subject);
        logger.info("Inside createdSubject(), createdSubject details are {}",createdSubject);
        return ResponseEntity.ok(createdSubject);
    }
    
    @PostMapping("/exam")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examRepository.save(exam));
    }
}
