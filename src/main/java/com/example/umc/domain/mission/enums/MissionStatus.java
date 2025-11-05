package com.example.umc.domain.mission.enums;

public enum MissionStatus {
    PROGRESSING("진행 중"),
    SUCCESS("성공");

    private final String label;

    MissionStatus(String label) {
        this.label = label;
    }
}