package com.programmers.dto.menu;

import com.programmers.domain.Food;
import com.programmers.domain.Menu;
import com.programmers.domain.Store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuRequestDto {
    private Store store;
    private Food food;
    private String storeName;
    private String foodName;

    @Builder
    public MenuRequestDto(Store store, Food food, String storeName, String foodName) {
        this.store = store;
        this.food = food;
        this.storeName = storeName;
        this.foodName = foodName;
    }

    public static MenuRequestDto of(Menu menu) {
        return MenuRequestDto.builder()
                .store(menu.getStore())
                .food(menu.getFood())
                .storeName(menu.getStoreName())
                .foodName(menu.getFoodName())
                .build();
    }

    public Menu toEntity() {
        return Menu.builder()
                .store(store)
                .food(food)
                .build();
    }
}
