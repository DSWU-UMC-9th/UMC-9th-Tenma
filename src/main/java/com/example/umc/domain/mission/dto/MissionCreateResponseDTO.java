package com.example.umc.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MissionCreateResponseDTO {

    private Long missionId;
    private Long storeId;
    private String condition;
    private Integer point;
    private LocalDateTime deadline;
}