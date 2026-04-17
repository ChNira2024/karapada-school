package com.karapada.school.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.school.dto.StudentProfileResponse;
import com.karapada.school.entity.Student;
import com.karapada.school.entity.academic.Attendance;
import com.karapada.school.entity.academic.Marks;
import com.karapada.school.exception.StudentNotFoundException;
import com.karapada.school.service.AttendanceService;
import com.karapada.school.service.IStudentService;
import com.karapada.school.service.MarksService;

@RestController
@RequestMapping("/student")
//@PreAuthorize("hasRole('STUDENT')")
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private MarksService marksService;

	@Autowired
	private IStudentService iStudentService;

	@GetMapping("/profile")
	public StudentProfileResponse viewProfile(Authentication authentication) {
		return iStudentService.viewMyProfile(authentication.getName());
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {

	    Student student = iStudentService.getStudentById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found"));

	    return ResponseEntity.ok(student);
	}

// 📌 Get Attendance
	@GetMapping("/{id}/attendance")
	public ResponseEntity<List<Attendance>> getAttendance(@PathVariable("id") Long id) {

		logger.info("Inside getAttendance(): StudentId {}", id);

		List<Attendance> attendanceList = attendanceService.getStudentAttendance(id);

		logger.info("Attendance fetched: {}", attendanceList.size());

		return ResponseEntity.ok(attendanceList);
	}

// 📌 Get Marks
	@GetMapping("/{id}/marks")
	public ResponseEntity<List<Marks>> getMarks(@PathVariable("id") Long id) {

		logger.info("Inside getMarks(): StudentId {}", id);

		List<Marks> marksList = marksService.getStudentMarks(id);

		logger.info("Marks fetched: {}", marksList.size());

		return ResponseEntity.ok(marksList);
	}
}