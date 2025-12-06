package com.example.umc.domain.auth.controller;

import com.example.umc.domain.auth.dto.LoginRequest;
import com.example.umc.domain.auth.dto.LoginResponse;
import com.example.umc.domain.auth.dto.SignupRequest;
import com.example.umc.domain.auth.dto.SignupResponse;
import com.example.umc.domain.auth.service.AuthService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /** 회원가입 */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody SignupRequest request) {

        SignupResponse response = authService.signup(request);

        return ResponseEntity
                .status(GeneralSuccessCode.USER_CREATED.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.USER_CREATED, response));
    }

    /** 로그인 */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request,
            HttpSession session
    ) {

        LoginResponse response = authService.login(request, session);

        return ResponseEntity
                .status(GeneralSuccessCode.AUTH_LOGIN_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.AUTH_LOGIN_SUCCESS, response));
    }

    /** 로그아웃 */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {

        authService.logout(session);

        return ResponseEntity
                .status(GeneralSuccessCode.AUTH_LOGOUT_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.AUTH_LOGOUT_SUCCESS, "로그아웃 성공"));
    }
}