package com.karapada.school.entity.academic;

import java.time.LocalDate;

import com.karapada.school.entity.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Attendance {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private ClassRoom classRoom;

    private LocalDate date;
    
    private boolean present;
}
