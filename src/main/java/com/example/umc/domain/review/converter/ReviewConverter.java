package com.example.umc.domain.review.converter;

import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResponseDTO toDto(Review review) {
        return ReviewResponseDTO.builder()
                .id(review.getId())
                .storeId(review.getStore().getId())
                .userName(review.getUser().getNickname())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .commentId(review.getComment() != null ? review.getComment().getId() : null)
                .commentContent(review.getComment() != null ? review.getComment().getContent() : null)
                .commentCreatedAt(review.getComment() != null ? review.getComment().getCreatedAt() : null)
                .build();
    }
}