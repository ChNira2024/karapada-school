package com.karapada.school.service.impl;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.karapada.school.dto.StudentProfileResponse;
import com.karapada.school.entity.Student;
import com.karapada.school.repository.StudentRepository;
import com.karapada.school.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
    	logger.info("StudentServiceImpl class: saveStudent() {}",student);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
    	logger.info("StudentServiceImpl class: getAllStudents() {}");
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
    	logger.info("StudentServiceImpl class: getStudentById() {}",id);
        return studentRepository.findById(id);
    }
    
    @Override
    public StudentProfileResponse viewMyProfile(String username) {

        Student s = studentRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Student not found"));

        StudentProfileResponse dto = new StudentProfileResponse();
        dto.setId(s.getId());
        dto.setUsername(s.getUsername());
        dto.setFullName(s.getFullName());
        dto.setEmail(s.getEmail());
        dto.setPhone(s.getPhone());
        

        dto.setAdmissionNumber(s.getAdmissionNumber());
        dto.setDateOfBirth(s.getDateOfBirth());
        dto.setGender(s.getGender());
        dto.setBloodGroup(s.getBloodGroup());
        dto.setParentName(s.getParentName());
        dto.setParentContact(s.getParentContact());

        return dto;
    }

}
