package com.karapada.school.service;

import java.util.List;
import java.util.Optional;

import com.karapada.school.dto.StudentProfileResponse;
import com.karapada.school.entity.Student;

public interface IStudentService {

	public Student saveStudent(Student user);
	public List<Student> getAllStudents();
	public Optional<Student> getStudentById(Long id);
    StudentProfileResponse viewMyProfile(String username);

}
