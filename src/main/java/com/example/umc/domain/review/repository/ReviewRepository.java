package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 가게 리뷰 전체
    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId")
    List<Review> findByStoreId(@Param("storeId") Long storeId);

    // 특정 유저의 리뷰
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId")
    List<Review> findByUserId(@Param("userId") Long userId);
}
