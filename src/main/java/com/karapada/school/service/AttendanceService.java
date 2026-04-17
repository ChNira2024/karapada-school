package com.karapada.school.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karapada.school.dto.AttendanceRequest;
import com.karapada.school.dto.AttendanceResponse;
import com.karapada.school.dto.StudentAttendanceDTO;
import com.karapada.school.entity.Student;
import com.karapada.school.entity.academic.Attendance;
import com.karapada.school.entity.academic.ClassRoom;
import com.karapada.school.entity.academic.Marks;
import com.karapada.school.repository.AttendanceRepository;
import com.karapada.school.repository.ClassRoomRepository;
import com.karapada.school.repository.MarksRepository;
import com.karapada.school.repository.StudentRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepo;

	@Autowired
	private ClassRoomRepository classRoomRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private MarksRepository marksRepository;

	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public List<Attendance> getStudentAttendance(Long studentId) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));

		return attendanceRepo.findByStudent(student);
	}

	

	public List<AttendanceResponse> markAttendance(AttendanceRequest request, String teacherUsername) {

		ClassRoom classRoom = classRoomRepository.findById(request.getClassRoomId())
				.orElseThrow(() -> new RuntimeException("Classroom not found"));

		LocalDate date = request.getDate();

		List<Attendance> list = new ArrayList<>();

		for (StudentAttendanceDTO dto : request.getAttendanceList()) {

			Student student = studentRepository.findById(dto.getStudentId())
					.orElseThrow(() -> new RuntimeException("Student not found"));

			Attendance attendance = new Attendance();
			attendance.setStudent(student);
			attendance.setClassRoom(classRoom);
			attendance.setDate(date);
			attendance.setPresent(dto.isPresent());

			list.add(attendance);
		}

		List<Attendance> saved = attendanceRepo.saveAll(list);

		// 🔥 IMPORTANT: CONVERT ENTITY → DTO
		return saved.stream().map(a -> {

			AttendanceResponse res = new AttendanceResponse();

			res.setId(a.getId());
			res.setDate(a.getDate());
			res.setPresent(a.isPresent());

			res.setStudentId(a.getStudent().getId());
			res.setStudentName(a.getStudent().getFullName());

			res.setClassRoomId(a.getClassRoom().getId());
			res.setStandard(a.getClassRoom().getStandard());
			res.setSection(a.getClassRoom().getSection());

			return res;

		}).toList();
	}
}