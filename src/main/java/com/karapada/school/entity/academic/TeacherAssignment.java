package com.karapada.school.entity.academic;

import com.karapada.school.entity.Teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TeacherAssignment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private ClassRoom classRoom;

    @ManyToOne
    private Subject subject;
}
