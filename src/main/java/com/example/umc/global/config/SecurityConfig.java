package com.example.umc.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())  // 폼 로그인 비활성화
                .httpBasic(basic -> basic.disable()) // Basic Auth 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // 회원가입/로그인 API 허용
                        .anyRequest().permitAll() // 지금은 전체 허용 (나중에 authenticated로 변경)
                )
                .sessionManagement(session -> session
                        .maximumSessions(1) // 동시 로그인 제한
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}