package com.example.umc.domain.user.entity;

import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.user.entity.mapping.UserFood;
import com.example.umc.domain.user.entity.mapping.UserMission;
import com.example.umc.domain.user.entity.mapping.UserTerm;
import com.example.umc.domain.user.enums.Sex;
import com.example.umc.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "phone_num", length = 20)
    private String phoneNum;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false)
    private Long point = 0L;

    @Column(nullable = false)
    private Long count_pass = 0L;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "deleted_at", nullable = false)
    private LocalDateTime deletedAt;

    // 관계
    @OneToMany(mappedBy = "user")
    private List<UserTerm> userTerms = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserFood> userFoods = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserMission> userMissions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();
}
