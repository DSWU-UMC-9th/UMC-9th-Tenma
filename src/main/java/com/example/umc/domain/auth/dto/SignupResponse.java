package com.example.umc.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupResponse {

    private Long userId;
    private String name;
    private String email;

    @Builder
    public SignupResponse(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}