package com.karapada.school.entity.academic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ClassRoom {

    @Id
    @GeneratedValue
    private Long id;

    private String standard;   // 1st, 2nd, 10th
    private String section;    // A, B, C
    
    @Column(name="academic_year")
    private String academicYear;
}
