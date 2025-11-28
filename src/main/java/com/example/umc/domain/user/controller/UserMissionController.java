package com.example.umc.domain.user.controller;

import com.example.umc.domain.user.dto.UserMissionRequestDTO;
import com.example.umc.domain.user.dto.UserMissionResponseDTO;
import com.example.umc.domain.user.service.UserMissionService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc.global.validation.ValidPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-missions")
public class UserMissionController {

    private final UserMissionService userMissionService;

    /** 미션 도전하기 */
    @PostMapping
    public ResponseEntity<ApiResponse<UserMissionResponseDTO>> startMission(
            @RequestBody UserMissionRequestDTO request
    ) {

        UserMissionResponseDTO response = userMissionService.startMission(request);

        return ResponseEntity
                .status(GeneralSuccessCode.USER_MISSION_CREATED.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.USER_MISSION_CREATED, response));
    }

    /** 내가 진행 중인 미션 목록 조회 */
    @GetMapping("/{userId}/missions/progressing")
    public ApiResponse<List<UserMissionResponseDTO>> getProgressingMissions(
            @PathVariable Long userId,
            @ValidPage Integer page
    ) {

        Page<UserMissionResponseDTO> response = userMissionService.getProgressingMissions(userId, page);

        return ResponseEntity
                .status(GeneralSuccessCode.USER_MISSION_READ_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.USER_MISSION_READ_SUCCESS, response));
    }
}