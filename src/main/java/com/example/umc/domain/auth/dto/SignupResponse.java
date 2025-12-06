package com.example.umc.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupResponse {

    private Long userId;
    private String email;

    @Builder
    public SignupResponse(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}