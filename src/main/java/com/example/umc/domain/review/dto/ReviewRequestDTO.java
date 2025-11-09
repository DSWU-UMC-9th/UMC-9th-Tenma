package com.example.umc.domain.review.dto;

import com.example.umc.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO{
    private Long userId;
    private Long storeId;
    private int star;
    private String content;
    private LocalDateTime createdAt;
}