package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.entity.QComment;
import com.example.umc.domain.review.entity.QReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewResponseDTO> findReviewsByStore(Long storeId, Integer star) {
        QReview review = QReview.review;
        QComment comment = QComment.comment;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(review.store.id.eq(storeId));

        if (star != null) {
            builder.and(review.star.eq(star));
        }

        return queryFactory
                .select(Projections.constructor(
                        ReviewResponseDTO.class,
                        review.user.nickname,
                        review.star,
                        review.content,
                        review.createdAt,
                        comment.content
                ))
                .from(review)
                .leftJoin(review.comment, comment)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}