package com.programmers.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreUpdateRequestDto {
    private Long storeId;
    private String storeName;
    private String category;
    private int reviewCount;
    private float rating;

    @Builder
    public StoreUpdateRequestDto(Long storeId, String storeName, String category, int reviewCount, float rating) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.category = category;
        this.reviewCount = reviewCount;
        this.rating = rating;
    }
}
