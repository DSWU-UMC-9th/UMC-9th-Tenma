package com.example.umc.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class HomeDTO {
    private String location;
    private Long myPoint;
    private Integer completeCount;
    private Integer goalCount;
    private List<HomeMissionDTO> missionList;
}