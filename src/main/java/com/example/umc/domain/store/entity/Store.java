package com.example.umc.domain.store.entity;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.store.entity.Category;
import com.example.umc.domain.store.entity.Region;
import com.example.umc.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    // 이 가게에 달린 리뷰들
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    // 이 가게가 가진 미션들
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Mission> missions = new ArrayList<>();
}