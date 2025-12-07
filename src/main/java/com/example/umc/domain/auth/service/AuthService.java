package com.example.umc.domain.auth.service;

import com.example.umc.domain.auth.dto.*;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.enums.Sex;
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

        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new GeneralException(GeneralErrorCode.AUTH_EMAIL_DUPLICATED);
        }

        // User 생성
        User user = User.builder()
                .name(request.getName())
                .nickname(request.getName())                 // 기본 닉네임 = 이름
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNum("010-0000-0000")                   // 임시 값
                .sex(Sex.FEMALE)                             // 기본 성별 지정 (필수 값)
                .birthDate(LocalDate.now())                  // 임시 값 → 수정 필요
                .address("주소 미등록")                        // 임시 값 → 수정 필요
                .point(0L)
                .count_pass(0L)
                .isDeleted(false)
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
        // JWT는 서버 세션을 사용하지 않기 때문에 기본적으로 아무 처리 없음
        // 필요하면 블랙리스트 또는 만료 처리 구현 가능
    }
}