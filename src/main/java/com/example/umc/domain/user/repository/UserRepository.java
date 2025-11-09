package com.example.umc.domain.user.repository;

import com.example.umc.domain.user.dto.MyPageDTO;
import com.example.umc.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}