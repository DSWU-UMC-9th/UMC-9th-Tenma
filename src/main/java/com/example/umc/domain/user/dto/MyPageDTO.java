package com.example.umc.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageDTO {
    private String nickname;
    private String email;
    private String phoneNum;
    private Long point;
}