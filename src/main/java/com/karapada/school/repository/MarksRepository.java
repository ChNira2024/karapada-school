package com.karapada.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karapada.school.entity.Student;
import com.karapada.school.entity.academic.Marks;

public interface MarksRepository extends JpaRepository<Marks, Long> {
	
    List<Marks> findByStudentId(Long studentId);

	List<Marks> findByStudent(Student student);

}
