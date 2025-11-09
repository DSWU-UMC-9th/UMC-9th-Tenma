package com.example.umc.domain.mission.dto;

import com.example.umc.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyMissionDTO {
    private Long userMissionId;
    private Long storeId;
    private String storeName;
    private Integer rewardPoint;
    private String condition;
    private MissionStatus missionStatus;
}