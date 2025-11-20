package com.example.umc.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewCreateResponseDTO {

    private Long reviewId;
    private Long storeId;
    private Long userId;
    private String userNickname;
    private Integer star;
    private String content;
    private LocalDateTime createdAt;
}