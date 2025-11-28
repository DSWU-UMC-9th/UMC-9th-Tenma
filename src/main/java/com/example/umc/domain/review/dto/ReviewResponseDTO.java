package com.example.umc.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDTO {

    private Long id;
    private Long storeId;
    private String userName;
    private Integer star;
    private String content;
    private LocalDateTime createdAt;

    private Long commentId;
    private String commentContent;
    private LocalDateTime commentCreatedAt;
}