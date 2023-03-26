package com.programmers.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreRequestDto {
    private String storeName;
    private String category;
    private int reviewCount;
    private float rating;

    @Builder
    public StoreRequestDto(String storeName, String category, int reviewCount, float rating) {
        this.storeName = storeName;
        this.category = category;
        this.reviewCount = reviewCount;
        this.rating = rating;
    }
}
