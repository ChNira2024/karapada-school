package com.karapada.school.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.karapada.school.dto.StudentResponse;
import com.karapada.school.dto.TeacherResponse;
import com.karapada.school.entity.Student;
import com.karapada.school.entity.Teacher;
import com.karapada.school.entity.academic.ClassRoom;
import com.karapada.school.entity.academic.Subject;
import com.karapada.school.enums.Role;
import com.karapada.school.exception.UsernameAlreadyExistsException;
import com.karapada.school.repository.ClassRoomRepository;
import com.karapada.school.repository.StudentRepository;
import com.karapada.school.repository.SubjectRepository;
import com.karapada.school.repository.TeacherRepository;

@Service
public class HeadmasterService {
	private static final Logger logger = LoggerFactory.getLogger(HeadmasterService.class);

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public TeacherResponse addTeacher(Teacher teacher) {
    	logger.info("Inside addTeacher(), HeadmasterService");
    	if (teacherRepository.findByUsername(teacher.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));

        teacher.setRole(Role.TEACHER);
        
        Teacher saved = teacherRepository.save(teacher);

        return mapToTeacherResponse(saved);
    }
    public TeacherResponse mapToTeacherResponse(Teacher teacher) {

        TeacherResponse res = new TeacherResponse();

        res.setId(teacher.getId());
        res.setUsername(teacher.getUsername());
        res.setFullName(teacher.getFullName());
        res.setEmail(teacher.getEmail());
        res.setPhone(teacher.getPhone());
        res.setRole(teacher.getRole().name());

        res.setEmployeeCode(teacher.getEmployeeCode());
        res.setQualification(teacher.getQualification());
        res.setSubjectSpecialization(teacher.getSubjectSpecialization());
        res.setExperienceYears(teacher.getExperienceYears());
        res.setJoiningDate(teacher.getJoiningDate());

        res.setActive(teacher.getActive());
        res.setCreatedAt(teacher.getCreatedAt());

        return res;
    }
    public StudentResponse addStudent(Student student) {

        if (studentRepository.findByUsername(student.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole(Role.STUDENT);

        String admissionNumber = "ADM-" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss"));

        student.setAdmissionNumber(admissionNumber);

        System.out.println("Generated Admission: " + admissionNumber);

        Student saved = studentRepository.save(student);

        return mapToStudentResponse(saved);
    }
    public StudentResponse mapToStudentResponse(Student student) {

        StudentResponse res = new StudentResponse();

        res.setId(student.getId());
        res.setUsername(student.getUsername());
        res.setFullName(student.getFullName());
        res.setEmail(student.getEmail());
        res.setPhone(student.getPhone());
        res.setRole(student.getRole().name());
        res.setAdmissionNumber(student.getAdmissionNumber());
        res.setDateOfBirth(student.getDateOfBirth());
        res.setGender(student.getGender());
        res.setBloodGroup(student.getBloodGroup());
        res.setParentName(student.getParentName());
        res.setParentContact(student.getParentContact());
        res.setActive(student.getActive());
        res.setCreatedAt(student.getCreatedAt());

        return res;
    }

    public ClassRoom createClassRoom(ClassRoom classRoom) {

        // 🔥 Duplicate check (ADD HERE)
        if (classRoomRepository
                .findByStandardAndSectionAndAcademicYear(classRoom.getStandard(),classRoom.getSection(),classRoom.getAcademicYear()).isPresent()) 
        {

             throw new RuntimeException(
            	    "Classroom already exists for " + classRoom.getStandard() + "-" + classRoom.getSection() + " (" + classRoom.getAcademicYear() + ")");
        }

        return classRoomRepository.save(classRoom);
    }

    public Subject createSubject(Subject subject) {
    	if (subjectRepository.findBySubjectCode(subject.getSubjectCode()).isPresent()) {
    	    throw new RuntimeException("Subject already exists");
    	}
        return subjectRepository.save(subject);
    }
}
