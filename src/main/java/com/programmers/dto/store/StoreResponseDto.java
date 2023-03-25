package com.programmers.dto.store;

import com.programmers.domain.Store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreResponseDto {
    private Long storeId;
    private String storeName;
    private String category;
    private int reviewCount;
    private float rating;

    @Builder
    public StoreResponseDto(Long storeId, String storeName, String category, int reviewCount, float rating) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.category = category;
        this.reviewCount = reviewCount;
        this.rating = rating;
    }

    public static StoreResponseDto of(Store store) {
        return StoreResponseDto.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .category(store.getCategory())
                .rating(store.getRating())
                .build();
    }
}
