package com.programmers.dto.menu;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuRequestDto {
    private final String storeName;
    private final String foodName;

    @Builder
    public MenuRequestDto(String storeName, String foodName) {
        this.storeName = storeName;
        this.foodName = foodName;
    }
}
