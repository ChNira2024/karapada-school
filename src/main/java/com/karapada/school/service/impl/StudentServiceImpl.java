package com.karapada.school.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.karapada.school.entity.Student;
import com.karapada.school.repository.StudentRepository;
import com.karapada.school.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{
	
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
