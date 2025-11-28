package com.example.umc.domain.user.converter;

import com.example.umc.domain.mission.enums.MissionStatus;
import com.example.umc.domain.user.dto.UserMissionResponseDTO;
import com.example.umc.domain.user.entity.mapping.UserMission;

public class UserMissionConverter {

    public static UserMissionResponseDTO toDto(UserMission um) {
        return UserMissionResponseDTO.builder()
                .userMissionId(um.getId())
                .missionId(um.getMission().getId())
                .storeId(um.getMission().getStore().getId())
                .condition(um.getMission().getCondition())
                .point(um.getMission().getPoint())
                .status(MissionStatus.valueOf(um.getStatus().name()))
                .deadline(um.getMission().getDeadline())
                .startedAt(um.getCreatedAt())
                .build();
    }
}