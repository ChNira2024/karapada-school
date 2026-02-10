package com.karapada.school.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karapada.school.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
