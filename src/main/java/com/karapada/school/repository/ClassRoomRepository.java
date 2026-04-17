package com.karapada.school.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karapada.school.entity.academic.ClassRoom;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
	Optional<ClassRoom> findByStandardAndSectionAndAcademicYear(String standard,String section,String academicYear
	);
}
