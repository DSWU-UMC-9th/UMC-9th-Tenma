package com.example.umc.domain.auth.service;

import com.example.umc.domain.auth.dto.LoginRequest;
import com.example.umc.domain.auth.dto.LoginResponse;
import com.example.umc.domain.auth.dto.SignupRequest;
import com.example.umc.domain.auth.dto.SignupResponse;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.repository.UserRepository;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /** 회원가입 */
    public SignupResponse signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new GeneralException(GeneralErrorCode.USER_CREATE_FAILED);
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User saved = userRepository.save(user);

        return SignupResponse.builder()
                .userId(saved.getId())
                .email(saved.getEmail())
                .build();
    }

    /** 로그인 (세션 저장) */
    public LoginResponse login(LoginRequest request, HttpSession session) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.AUTH_LOGIN_FAILED));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new GeneralException(GeneralErrorCode.AUTH_LOGIN_FAILED);
        }

        // 세션 저장
        session.setAttribute("LOGIN_USER", user.getId());

        return LoginResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .message("로그인 성공")
                .build();
    }

    /** 로그아웃 */
    public void logout(HttpSession session) {
        session.invalidate();
    }
}