package com.example.umc.global.config;

import com.example.umc.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    /** 🔐 비밀번호 암호화기 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** 🔐 인증 매니저 */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    /** 🔐 Spring Security 메인 설정 */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 🔥 CSRF 비활성화 (Form 로그인일 때만 활성화 권장)
                .csrf(csrf -> csrf.disable())

                // 🔥 요청 허용/차단 규칙
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/signup",
                                "/api/auth/login",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // 🔐 로그인 설정 (폼 방식 / 세션 방식)
                .formLogin(form -> form
                        .loginPage("/api/auth/login")   // 커스텀 로그인 페이지
                        .loginProcessingUrl("/api/auth/login") // 스프링이 로그인 처리하는 URL
                        .usernameParameter("email")       // 파라미터 이름(email 사용)
                        .passwordParameter("password")
                        .defaultSuccessUrl("/api/auth/login-success", true)
                        .failureUrl("/api/auth/login-fail")
                        .permitAll()
                )

                // 🔐 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/api/auth/logout-success")
                        .permitAll()
                )

                // 🔐 기본 세션 정책
                .sessionManagement(session ->
                        session.maximumSessions(1) // 동시 로그인 1명 제한
                                .maxSessionsPreventsLogin(false)
                );

        return http.build();
    }
}