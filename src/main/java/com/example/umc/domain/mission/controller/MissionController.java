package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.dto.MissionCreateRequestDTO;
import com.example.umc.domain.mission.dto.MissionCreateResponseDTO;
import com.example.umc.domain.mission.dto.MissionListResponseDTO;
import com.example.umc.domain.mission.service.MissionService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;

import com.example.umc.global.validation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "특정 가게의 미션 생성")
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

    @Operation(summary = "특정 가게의 미션 목록 조회")
    @GetMapping("/{storeId}/missions")
    public ResponseEntity<ApiResponse<List<MissionListResponseDTO>>> getMissions(
            @PathVariable Long storeId,
            @ValidPage Integer page
    ) {

        List<MissionListResponseDTO> response = missionService.getMissionList(storeId, page);

        return ResponseEntity
                .status(GeneralSuccessCode.MISSION_READ_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.MISSION_READ_SUCCESS, response));
    }
}