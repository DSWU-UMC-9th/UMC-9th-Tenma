package com.example.umc.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "미션 조회 응답 DTO")
@Getter
@Builder
public class MissionListResponseDTO {

    @Schema(description = "미션 ID")
    private Long missionId;

    @Schema(description = "미션 조건")
    private String condition;

    @Schema(description = "보상 포인트")
    private Integer point;

    @Schema(description = "마감 시간")
    private LocalDateTime deadline;
}