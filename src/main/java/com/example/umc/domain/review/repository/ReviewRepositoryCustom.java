package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.dto.ReviewResponseDTO;
import java.util.List;

public interface ReviewRepositoryCustom {
    List<ReviewResponseDTO> findReviewsByStore(Long storeId, Integer star);
}