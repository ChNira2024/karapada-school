package com.karapada.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karapada.school.entity.Headmaster;

public interface HeadmasterRepository extends JpaRepository<Headmaster, Long> {

    // 🔍 used for login
    Optional<Headmaster> findByUsername(String username);
	
}
