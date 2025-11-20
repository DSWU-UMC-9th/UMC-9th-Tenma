package com.example.umc.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCreateRequestDTO {

    private String name;
    private Long categoryId;
}