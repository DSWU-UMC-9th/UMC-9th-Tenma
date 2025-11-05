package com.example.umc.domain.user.enums;

public enum Food {
    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    SNACKFOOD("분식"),
    DESSERT("디저트"),
    FASTFOOD("패스트푸드");


    private final String label;

    Food(final String label) {
        this.label = label;
    }
}
