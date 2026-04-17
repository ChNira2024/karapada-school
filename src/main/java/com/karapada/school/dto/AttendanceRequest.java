package com.karapada.school.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
@Data
public class AttendanceRequest {

	 private Long classRoomId;

	    private LocalDate date;

	    private List<StudentAttendanceDTO> attendanceList;
}
