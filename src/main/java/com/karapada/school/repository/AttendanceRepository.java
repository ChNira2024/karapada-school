package com.karapada.school.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karapada.school.entity.Student;
import com.karapada.school.entity.academic.Attendance;
import com.karapada.school.entity.academic.ClassRoom;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	
    List<Attendance> findByClassRoomAndDate(ClassRoom classRoom, LocalDate date);

	List<Attendance> findByStudent(Student student);

	List<Attendance> findByStudentId(Long studentId);

}