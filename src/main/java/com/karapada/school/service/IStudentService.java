package com.karapada.school.service;

import java.util.List;

import com.karapada.school.entity.Student;

public interface IStudentService {

	public Student saveStudent(Student user);
	public List<Student> getAllStudents();
	public Student getStudentById(Long id);
}
