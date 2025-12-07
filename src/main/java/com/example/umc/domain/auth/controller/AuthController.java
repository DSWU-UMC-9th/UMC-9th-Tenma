package com.example.umc.domain.auth.controller;

import com.example.umc.domain.auth.dto.*;
import com.example.umc.domain.auth.service.AuthService;

import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /** 회원가입 */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(
            @RequestBody SignupRequest request
    ) {
        SignupResponse response = authService.signup(request);

        return ResponseEntity
                .status(GeneralSuccessCode.AUTH_SIGNUP_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.AUTH_SIGNUP_SUCCESS, response));
    }

    /** 로그인 */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request
    ) {
        LoginResponse response = authService.login(request);

        return ResponseEntity
                .status(GeneralSuccessCode.AUTH_LOGIN_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.AUTH_LOGIN_SUCCESS, response));
    }

    /** 로그아웃 (프론트에서 토큰만 삭제) */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {

        authService.logout();

        return ResponseEntity
                .status(GeneralSuccessCode.AUTH_LOGOUT_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.AUTH_LOGOUT_SUCCESS, "로그아웃 완료"));
    }
}