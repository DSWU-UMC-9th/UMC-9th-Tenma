package com.example.umc.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreateRequestDTO {

    private Long userId;
    private Integer star;
    private String content;
}