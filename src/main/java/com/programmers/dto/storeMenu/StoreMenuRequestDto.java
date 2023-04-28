package com.programmers.dto.storeMenu;

import com.programmers.domain.food.Food;
import com.programmers.domain.storeMenu.StoreMenu;
import com.programmers.domain.store.Store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreMenuRequestDto {
    private Store store;
    private Food food;
    private String storeName;
    private String foodName;

    @Builder
    public StoreMenuRequestDto(Store store, Food food, String storeName, String foodName) {
        this.store = store;
        this.food = food;
        this.storeName = storeName;
        this.foodName = foodName;
    }

    public static StoreMenuRequestDto of(StoreMenu storeMenu) {
        return StoreMenuRequestDto.builder()
                .store(storeMenu.getStore())
                .food(storeMenu.getFood())
                .storeName(storeMenu.getStore().getStoreName())
                .foodName(storeMenu.getFood().getName())
                .build();
    }

    public StoreMenu toEntity() {
        return StoreMenu.builder()
                .store(store)
                .food(food)
                .build();
    }
}
