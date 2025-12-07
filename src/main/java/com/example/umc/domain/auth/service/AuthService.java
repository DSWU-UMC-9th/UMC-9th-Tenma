package com.example.umc.domain.auth.service;

import com.example.umc.domain.auth.dto.*;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.repository.UserRepository;

import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;

import com.example.umc.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /** 회원가입 */
    public SignupResponse signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new GeneralException(GeneralErrorCode.USER_CREATE_FAILED);
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname("default")      // 임시
                .address("none")         // 임시
                .sex(null)               // 임시
                .birthDate(LocalDate.now()) // 임시
                .build();

        User saved = userRepository.save(user);

        return SignupResponse.builder()
                .userId(saved.getId())
                .email(saved.getEmail())
                .name(saved.getName())
                .build();
    }

    /** 로그인 */
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new GeneralException(GeneralErrorCode.AUTH_LOGIN_FAILED);
        }

        String token = jwtTokenProvider.createToken(user.getId(), user.getEmail());

        return LoginResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .token(token)
                .build();
    }

    /** 로그아웃 */
    public void logout() {
    }
}