package com.karapada.school.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MarksResponse {

    private Long studentId;
    private String studentName;

    private Long subjectId;
    private String subjectName;

    private Integer marks;
    private LocalDate examDate;
}