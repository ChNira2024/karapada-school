package com.karapada.school.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudentResponse {

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private String admissionNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String parentName;
    private String parentContact;
    private Boolean active;
    private LocalDateTime createdAt;
}