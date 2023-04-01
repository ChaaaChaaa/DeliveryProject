package com.programmers.dto.store;

import com.programmers.domain.Store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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

    public static StoreRequestDto of(Store store) {
        return StoreRequestDto.builder()
                .storeName(store.getStoreName())
                .category(store.getCategory())
                .reviewCount(store.getReviewCount())
                .rating(store.getRating())
                .build();
    }

    public Store toEntity() {
        return Store.builder()
                .storeName(storeName)
                .category(category)
                .reviewCount(reviewCount)
                .rating(rating)
                .build();
    }
}
