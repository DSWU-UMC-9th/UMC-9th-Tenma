package com.example.umc.domain.store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreCreateResponseDTO {
    private Long storeId;
    private Long regionId;
    private Long categoryId;
    private String name;
}