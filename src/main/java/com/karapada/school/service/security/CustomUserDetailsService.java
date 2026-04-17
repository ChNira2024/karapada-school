package com.karapada.school.service.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.karapada.school.entity.BaseUser;
import com.karapada.school.entity.Headmaster;
import com.karapada.school.entity.Student;
import com.karapada.school.entity.Teacher;
import com.karapada.school.repository.HeadmasterRepository;
import com.karapada.school.repository.StudentRepository;
import com.karapada.school.repository.TeacherRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private StudentRepository studentRepo;
    @Autowired private TeacherRepository teacherRepo;
    @Autowired private HeadmasterRepository headmasterRepo;

    // ✅ REQUIRED by Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BaseUser user = findByUsername(username);
        System.out.println("user: "+user);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())) // 🔥 FIX HERE
        );
    }

    // ✅ Common method (your logic reused)
    public BaseUser findByUsername(String username) {

        Optional<Student> student = studentRepo.findByUsername(username);
        System.out.println("student: "+student);
        if (student.isPresent()) return student.get();

        Optional<Teacher> teacher = teacherRepo.findByUsername(username);
        System.out.println("teacher: "+teacher);
        if (teacher.isPresent()) return teacher.get();

        Optional<Headmaster> hm = headmasterRepo.findByUsername(username);
        System.out.println("hm: "+hm);
        if (hm.isPresent()) return hm.get();

        throw new UsernameNotFoundException("User not found");
    }
}