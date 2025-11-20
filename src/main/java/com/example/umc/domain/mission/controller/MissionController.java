package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.dto.MissionCreateRequestDTO;
import com.example.umc.domain.mission.dto.MissionCreateResponseDTO;
import com.example.umc.domain.mission.service.MissionService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/{storeId}/missions")
    public ResponseEntity<ApiResponse<MissionCreateResponseDTO>> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionCreateRequestDTO request
    ) {

        MissionCreateResponseDTO response = missionService.createMission(storeId, request);

        return ResponseEntity
                .status(GeneralSuccessCode.MISSION_CREATED.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.MISSION_CREATED, response));
    }
}