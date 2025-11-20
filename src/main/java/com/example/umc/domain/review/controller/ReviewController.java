package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.ReviewCreateRequestDTO;
import com.example.umc.domain.review.dto.ReviewCreateResponseDTO;
import com.example.umc.domain.review.service.ReviewService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

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
}