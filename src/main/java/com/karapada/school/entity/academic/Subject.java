package com.karapada.school.entity.academic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="subject_code")
    private String subjectCode;
    @Column(name="subject_name")
    private String subjectName;
}
