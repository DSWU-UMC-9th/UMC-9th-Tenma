package com.example.umc.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private Long userId;
    private String nickname;
    private String message;

    @Builder
    public LoginResponse(Long userId, String nickname, String message) {
        this.userId = userId;
        this.nickname = nickname;
        this.message = message;
    }
}