package com.example.umc.domain.user.entity;

import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.user.entity.mapping.UserFood;
import com.example.umc.domain.user.entity.mapping.UserMission;
import com.example.umc.domain.user.entity.mapping.UserTerm;
import com.example.umc.domain.user.enums.Sex;
import com.example.umc.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

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

    @Column(name = "deleted_at")
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

    @Builder
    public User(String name,
                String nickname,
                String email,
                String password,
                String phoneNum,
                Sex sex,
                LocalDate birthDate,
                String address,
                Long point,
                Long count_pass,
                Boolean isDeleted,
                LocalDateTime deletedAt) {

        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.sex = sex;
        this.birthDate = birthDate;
        this.address = address;
        this.point = point;
        this.count_pass = count_pass;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }
}