package com.example.umc.domain.user.dto;

import com.example.umc.domain.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserMissionResponseDTO {

    private Long userMissionId;
    private Long missionId;
    private Long storeId;
    private String condition;
    private Integer point;
    private MissionStatus status;
    private LocalDateTime deadline;
    private LocalDateTime startedAt;   // BaseEntity.createdAt
}