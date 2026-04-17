package com.karapada.school.entity.academic;

import java.time.LocalDate;

import com.karapada.school.entity.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "subject_id", "exam_id"})
    }
)
public class Marks {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private ClassRoom classRoom;

    private Integer marksObtained;

    private Integer maxMarks;

    private LocalDate createdAt;
}