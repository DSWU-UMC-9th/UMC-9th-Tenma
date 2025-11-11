package com.example.umc.domain.review.service;

import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDTO> getReviewsByStore(Long storeId, Integer star) {
        return reviewRepository.findReviewsByStore(storeId, star);
    }
}