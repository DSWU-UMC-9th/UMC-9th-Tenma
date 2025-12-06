package com.example.umc.domain.auth.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class SignupRequest {

    private String name;
    private String email;
    private String password;

    @Builder
    public SignupRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}