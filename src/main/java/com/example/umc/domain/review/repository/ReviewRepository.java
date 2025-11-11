package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    @Query("""
        SELECT new com.example.umc.domain.review.dto.ReviewResponseDTO(
            r.id,
            r.user.name,
            r.star,
            r.content,
            r.createdAt,
            c.id,
            c.content,
            c.createdAt
        )
        FROM Review r
        LEFT JOIN r.comment c
        WHERE r.store.id = :storeId
        ORDER BY r.createdAt DESC
    """)
    Page<ReviewResponseDTO> findReviewByStoreId(@Param("storeId") Long storeId, Pageable pageable);
}