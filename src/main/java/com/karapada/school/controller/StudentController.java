package com.karapada.school.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.school.entity.Student;
import com.karapada.school.service.IStudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	private IStudentService iStudentService;
	
	public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }


    @PostMapping("/add-student")
    public Student createStudent(@RequestBody Student student) {
    	System.out.println("studen"+student);
        return iStudentService.saveStudent(student);
    }

    @GetMapping("/get-all-students")
    public List<Student> getStudents() {
        return iStudentService.getAllStudents();
    }

    @GetMapping("get-id/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return iStudentService.getStudentById(id);
    }
}

