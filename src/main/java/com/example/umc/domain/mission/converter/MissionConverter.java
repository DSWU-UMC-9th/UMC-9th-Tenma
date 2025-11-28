package com.example.umc.domain.mission.converter;

import com.example.umc.domain.mission.dto.MissionListResponseDTO;
import com.example.umc.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static List<MissionListResponseDTO> toListDTO(List<Mission> missions) {
        return missions.stream()
                .map(m -> MissionListResponseDTO.builder()
                        .missionId(m.getId())
                        .condition(m.getCondition())
                        .point(m.getPoint())
                        .deadline(m.getDeadline())
                        .build()
                )
                .collect(Collectors.toList());
    }
}