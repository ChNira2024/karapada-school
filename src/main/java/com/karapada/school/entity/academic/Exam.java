package com.karapada.school.entity.academic;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Exam {

    @Id
    @GeneratedValue
    private Long id;

    private String examName;

    private LocalDate examDate;  // ✅ ADD THIS
}