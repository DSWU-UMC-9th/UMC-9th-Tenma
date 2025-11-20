package com.example.umc.domain.store.controller;

import com.example.umc.domain.store.dto.StoreCreateRequestDTO;
import com.example.umc.domain.store.dto.StoreCreateResponseDTO;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.service.StoreService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/region/{regionId}/stores")
    public ResponseEntity<ApiResponse<StoreCreateResponseDTO>> createStore(
            @PathVariable Long regionId,
            @RequestBody StoreCreateRequestDTO request
    ) {

        Store store = storeService.createStore(regionId, request);

        StoreCreateResponseDTO response = StoreCreateResponseDTO.builder()
                .storeId(store.getId())
                .regionId(regionId)
                .categoryId(store.getCategory().getId())
                .name(store.getName())
                .build();

        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, response));
    }
}