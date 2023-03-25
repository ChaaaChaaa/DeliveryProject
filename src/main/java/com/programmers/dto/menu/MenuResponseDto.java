package com.programmers.dto.menu;

import com.programmers.domain.Menu;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MenuResponseDto {
    private String storeName;
    private String foodName;

    @Builder
    public MenuResponseDto(String storeName, String foodName) {
        this.storeName = storeName;
        this.foodName = foodName;
    }

    public static MenuResponseDto convertIdToName(Menu menu) {
        return MenuResponseDto.builder()
                .foodName(menu.getFood().getName())
                .storeName(menu.getStore().getStoreName())
                .build();
    }
}
