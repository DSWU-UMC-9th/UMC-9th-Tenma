package com.example.umc.domain.review.service;

import com.example.umc.domain.review.dto.ReviewCreateRequestDTO;
import com.example.umc.domain.review.dto.ReviewCreateResponseDTO;
import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.repository.StoreRepository;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.repository.UserRepository;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewCreateResponseDTO createReview(Long storeId, ReviewCreateRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.STORE_NOT_FOUND));

        try {
            Review review = new Review();
            review.setUser(user);
            review.setStore(store);
            review.setStar(request.getStar());
            review.setContent(request.getContent());

            Review saved = reviewRepository.save(review);

            return ReviewCreateResponseDTO.builder()
                    .reviewId(saved.getId())
                    .storeId(storeId)
                    .userId(user.getId())
                    .userNickname(user.getNickname())
                    .star(saved.getStar())
                    .content(saved.getContent())
                    .createdAt(saved.getCreatedAt())
                    .build();

        } catch (Exception e) {
            throw new GeneralException(GeneralErrorCode.REVIEW_CREATE_FAILED);
        }
    }

    public List<ReviewResponseDTO> getReviewsByStore(Long storeId, Integer star) {
        return reviewRepository.findReviewsByStore(storeId, star);
    }
}