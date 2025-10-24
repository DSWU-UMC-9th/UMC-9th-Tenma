package com.example.umc.global.entity;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalTime;

public class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalTime updatedAt;
}
