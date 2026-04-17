package com.karapada.school.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Teacher extends BaseUser {

	
    private String employeeCode;
    private String qualification;
    private String subjectSpecialization;
    private Integer experienceYears;
    private LocalDate joiningDate;
}
