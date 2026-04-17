package com.karapada.school.entity;

import java.time.LocalDateTime;

import com.karapada.school.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ✅ VERY IMPORTANT
    protected Long id;

    @Column(unique = true, nullable = false)
    protected String username;

    @Column(nullable = false)
    protected String password;

    protected String fullName;
    protected String email;
    protected String phone;

    @Enumerated(EnumType.STRING)
    protected Role role;

    @Column(nullable = false)
    protected Boolean active;

    @Column(nullable = false)
    protected LocalDateTime createdAt;

    @PrePersist   // ✅ recommended (auto values)
    protected void onCreate() {
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }
}
