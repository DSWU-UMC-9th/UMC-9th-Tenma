package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.dto.MissionCreateRequestDTO;
import com.example.umc.domain.mission.dto.MissionCreateResponseDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.repository.MissionRepository;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.repository.StoreRepository;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public MissionCreateResponseDTO createMission(Long storeId, MissionCreateRequestDTO request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.STORE_NOT_FOUND));

        try {
            Mission mission = new Mission();
            mission.setStore(store);
            mission.setCondition(request.getCondition());
            mission.setPoint(request.getPoint());
            mission.setDeadline(request.getDeadline());

            Mission saved = missionRepository.save(mission);

            return MissionCreateResponseDTO.builder()
                    .missionId(saved.getId())
                    .storeId(storeId)
                    .condition(saved.getCondition())
                    .point(saved.getPoint())
                    .deadline(saved.getDeadline())
                    .build();

        } catch (Exception e) {
            throw new GeneralException(GeneralErrorCode.MISSION_CREATE_FAILED);
        }
    }
}