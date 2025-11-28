package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.converter.MissionConverter;
import com.example.umc.domain.mission.dto.MissionCreateRequestDTO;
import com.example.umc.domain.mission.dto.MissionCreateResponseDTO;
import com.example.umc.domain.mission.dto.MissionListResponseDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.repository.MissionRepository;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.repository.StoreRepository;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    /** 가게별 미션 생성하기 */
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

    /** 가게별 미션 목록 조회  */
    public List<MissionListResponseDTO> getMissionList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        var pageable = PageRequest.of(page, 10);

        var missionPage = missionRepository.findByStore(store, pageable);

        return MissionConverter.toListDTO(missionPage.getContent());
    }
}