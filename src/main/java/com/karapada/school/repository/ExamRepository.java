package com.karapada.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karapada.school.entity.academic.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {}
