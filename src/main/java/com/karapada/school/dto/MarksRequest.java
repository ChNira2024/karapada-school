package com.karapada.school.dto;

import java.util.List;

import lombok.Data;
@Data
public class MarksRequest {

    private Long classRoomId;
    private Long subjectId;
    private Long examId; 

    private Integer maxMarks;

    private List<StudentMarksDTO> studentMarks;
}