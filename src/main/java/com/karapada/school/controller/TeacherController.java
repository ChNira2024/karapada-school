package com.karapada.school.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.school.dto.AttendanceRequest;
import com.karapada.school.dto.AttendanceResponse;
import com.karapada.school.dto.MarksRequest;
import com.karapada.school.dto.MarksResponse;
import com.karapada.school.service.AttendanceService;
import com.karapada.school.service.MarksService;

@RestController
@RequestMapping("/teacher")
@PreAuthorize("hasRole('TEACHER')")
public class TeacherController {
	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
	
    @Autowired private AttendanceService attendanceService;
    @Autowired private MarksService marksService;

  
    //Mark attendance
    @PostMapping("/attendance")
    public ResponseEntity<?> markAttendance(@RequestBody AttendanceRequest request,Authentication authentication) {
    	logger.info("Inside markAttendance(), TeacherController");
        // 👇 teacher username from JWT token
        String teacherUsername = authentication.getName();

        List<AttendanceResponse> attendanceList = attendanceService.markAttendance(request, teacherUsername);
        logger.info("Inside markAttendance(), markAttendance details are {}",attendanceList);

        return ResponseEntity.ok(attendanceList);
    }

   
    @PostMapping("/marks")
    public ResponseEntity<List<MarksResponse>> uploadMarks(@RequestBody MarksRequest request,Authentication authentication) {
    	logger.info("Inside uploadMarks(), TeacherController");
        String teacherUsername = authentication.getName();

        List<MarksResponse> response =marksService.uploadMarks(request, teacherUsername);
        logger.info("Inside uploadMarks(), uploadMarks details are {}",response);
        return ResponseEntity.ok(response);
    }
}
