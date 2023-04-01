package com.programmers.dto.menu;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programmers.domain.Food;
import com.programmers.domain.Store;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuRequestDto {
    private final Store store;
    private final Food food;
    private final String storeName;
    private final String foodName;
    @Builder
    public MenuRequestDto(Store store, Food food, String storeName, String foodName) {
        this.store = store;
        this.food = food;
        this.storeName = storeName;
        this.foodName = foodName;
    }
}
