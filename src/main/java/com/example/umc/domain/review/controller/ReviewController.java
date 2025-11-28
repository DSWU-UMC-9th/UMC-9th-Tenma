package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.ReviewCreateRequestDTO;
import com.example.umc.domain.review.dto.ReviewCreateResponseDTO;
import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.service.ReviewService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc.global.validation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    /** 리뷰 작성 */
    @Operation(summary = "리뷰 작성")
    @PostMapping("/stores/{storeId}/reviews")
    public ResponseEntity<ApiResponse<ReviewCreateResponseDTO>> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewCreateRequestDTO request
    ) {

        ReviewCreateResponseDTO response = reviewService.createReview(storeId, request);

        return ResponseEntity
                .status(GeneralSuccessCode.REVIEW_CREATED.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_CREATED, response));
    }

    /** 사용자별 리뷰 목록 조회 */
    @Operation(summary = "특정 유저가 작성한 리뷰 목록 조회")
    @GetMapping("/{userId}/reviews")
    public ResponseEntity<ApiResponse<Page<ReviewResponseDTO>>> getUserReviews(
            @PathVariable Long userId,
            @ValidPage Integer page
    ) {

        Page<ReviewResponseDTO> response = reviewService.getUserReviews(userId, page);

        return ResponseEntity
                .status(GeneralSuccessCode.REVIEW_READ_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_READ_SUCCESS, response));
    }
}