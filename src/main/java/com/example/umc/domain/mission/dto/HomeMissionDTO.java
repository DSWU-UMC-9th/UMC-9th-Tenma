package com.example.umc.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class HomeMissionDTO {
    private Long missionId;
    private String storeName;
    private String category;
    private LocalDateTime deadLine;
    private String condition;
    private Integer rewardPoint;
}
