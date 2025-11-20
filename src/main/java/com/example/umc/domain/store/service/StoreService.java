package com.example.umc.domain.store.service;

import com.example.umc.domain.store.dto.StoreCreateRequestDTO;
import com.example.umc.domain.store.entity.Category;
import com.example.umc.domain.store.entity.Region;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.repository.CategoryRepository;
import com.example.umc.domain.store.repository.RegionRepository;
import com.example.umc.domain.store.repository.StoreRepository;

import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Store createStore(Long regionId, StoreCreateRequestDTO request) {

        Region region = regionRepository.findById(regionId)
                .orElseThrow(() ->
                        new GeneralException(GeneralErrorCode.STORE_REGION_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new GeneralException(GeneralErrorCode.STORE_CATEGORY_NOT_FOUND));

        try {
            Store store = new Store();
            store.setName(request.getName());
            store.setRegion(region);
            store.setCategory(category);

            return storeRepository.save(store);

        } catch (Exception e) {
            throw new GeneralException(GeneralErrorCode.STORE_CREATE_FAILED);
        }
    }
}