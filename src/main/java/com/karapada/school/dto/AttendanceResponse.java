package com.karapada.school.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class AttendanceResponse {

    private Long id;
    private LocalDate date;
    private boolean present;

    private Long studentId;
    private String studentName;

    private Long classRoomId;
    private String standard;
    private String section;
}
