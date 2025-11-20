package com.example.umc.domain.mission.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MissionCreateRequestDTO {

    private String condition;
    private Integer point;
    private LocalDateTime deadline;
}