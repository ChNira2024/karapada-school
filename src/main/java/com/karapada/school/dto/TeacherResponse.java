package com.karapada.school.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class TeacherResponse {
	private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String role;

    private String employeeCode;
    private String qualification;
    private String subjectSpecialization;
    private Integer experienceYears;
    private LocalDate joiningDate;

    private Boolean active;
    private LocalDateTime createdAt;

}
