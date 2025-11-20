package com.example.umc.domain.user.controller;

import com.example.umc.domain.user.dto.UserMissionRequestDTO;
import com.example.umc.domain.user.dto.UserMissionResponseDTO;
import com.example.umc.domain.user.service.UserMissionService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-missions")
public class UserMissionController {

    private final UserMissionService userMissionService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserMissionResponseDTO>> startMission(
            @RequestBody UserMissionRequestDTO request
    ) {

        UserMissionResponseDTO response = userMissionService.startMission(request);

        return ResponseEntity
                .status(GeneralSuccessCode.USER_MISSION_CREATED.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.USER_MISSION_CREATED, response));
    }
}