package com.example.umc.domain.user.dto;

import com.example.umc.domain.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserMissionResponseDTO {

    private Long userMissionId;
    private Long userId;
    private Long missionId;
    private MissionStatus status;
}