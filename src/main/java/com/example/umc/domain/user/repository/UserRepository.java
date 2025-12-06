package com.example.umc.domain.user.repository;

import com.example.umc.domain.user.dto.MyPageDTO;
import com.example.umc.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT new com.example.umc.domain.user.dto.MyPageDTO(
            u.nickname,
            u.email,
            u.phoneNum,
            u.point
        )
        FROM User u
        WHERE u.id = :userId
    """)
    MyPageDTO findMypageInfo(@Param("userId") Long userId);

    Optional<User> findByEmail(String email); // 로그인 시 유저 찾기

    boolean existsByEmail(String email); // 회원가입 시 중복 체크
}