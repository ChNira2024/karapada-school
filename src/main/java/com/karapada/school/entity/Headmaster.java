package com.karapada.school.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Headmaster extends BaseUser {

    private String qualification;
    private Integer experienceYears;
    private LocalDate joiningDate;
}
