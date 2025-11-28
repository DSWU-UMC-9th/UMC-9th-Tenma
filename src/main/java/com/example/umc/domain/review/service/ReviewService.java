package com.example.umc.domain.review.service;

import com.example.umc.domain.review.converter.ReviewConverter;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    /** 리뷰 작성하기 */
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

    /** 사용자별 리뷰 목록 조회 */
    public Page<ReviewResponseDTO> getUserReviews(Long userId, int page) {

        if (!userRepository.existsById(userId)) {
            throw new GeneralException(GeneralErrorCode.USER_NOT_FOUND);
        }

        PageRequest pageable = PageRequest.of(page, 10);
        Page<Review> reviewPage = reviewRepository.findByUserId(userId, pageable);

        if (reviewPage.isEmpty()) {
            throw new GeneralException(GeneralErrorCode.REVIEW_LIST_EMPTY);
        }

        return reviewPage.map(ReviewConverter::toDto);
    }
}