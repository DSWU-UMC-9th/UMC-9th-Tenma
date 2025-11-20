package com.example.umc.domain.user.service;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.enums.MissionStatus;
import com.example.umc.domain.mission.repository.MissionRepository;
import com.example.umc.domain.user.dto.UserMissionRequestDTO;
import com.example.umc.domain.user.dto.UserMissionResponseDTO;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.entity.mapping.UserMission;
import com.example.umc.domain.user.repository.UserMissionRepository;
import com.example.umc.domain.user.repository.UserRepository;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Transactional
    public UserMissionResponseDTO startMission(UserMissionRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MISSION_NOT_FOUND));

        try {
            UserMission userMission = UserMission.builder()
                    .user(user)
                    .mission(mission)
                    .status(MissionStatus.PROGRESSING)
                    .build();

            UserMission saved = userMissionRepository.save(userMission);

            return UserMissionResponseDTO.builder()
                    .userMissionId(saved.getId())
                    .userId(user.getId())
                    .missionId(mission.getId())
                    .status(saved.getStatus())
                    .build();

        } catch (Exception e) {
            throw new GeneralException(GeneralErrorCode.USER_MISSION_CREATE_FAILED);
        }
    }
}