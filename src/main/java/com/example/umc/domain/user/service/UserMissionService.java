package com.example.umc.domain.user.service;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.enums.MissionStatus;
import com.example.umc.domain.mission.repository.MissionRepository;
import com.example.umc.domain.user.converter.UserMissionConverter;
import com.example.umc.domain.user.dto.UserMissionRequestDTO;
import com.example.umc.domain.user.dto.UserMissionResponseDTO;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.entity.mapping.UserMission;
import com.example.umc.domain.user.repository.UserMissionRepository;
import com.example.umc.domain.user.repository.UserRepository;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    /** 미션 도전하기 */
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
                    .missionId(mission.getId())
                    .storeId(mission.getStore().getId())
                    .condition(mission.getCondition())
                    .point(mission.getPoint())
                    .deadline(mission.getDeadline())
                    .status(saved.getStatus())
                    .startedAt(saved.getCreatedAt())
                    .build();

        } catch (Exception e) {
            throw new GeneralException(GeneralErrorCode.USER_MISSION_CREATE_FAILED);
        }
    }

    /** 내가 진행중인 미션 목록 조회 */
    public Page<UserMissionResponseDTO> getProgressingMissions(Long userId, int page) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        Page<UserMission> userMissionPage = userMissionRepository.findByUserIdAndStatus(
                userId,
                MissionStatus.PROGRESSING,
                PageRequest.of(page, 10)
        );

        if (userMissionPage.isEmpty()) {
            throw new GeneralException(GeneralErrorCode.USER_MISSION_LIST_EMPTY);
        }

        return userMissionPage.map(UserMissionConverter::toDto);
    }
}