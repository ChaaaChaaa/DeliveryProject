package com.programmers.dto.food;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FoodUpdateRequestDto {
    private Long id;
    private String name;
    private int price;
    private String description;

    @Builder
    public FoodUpdateRequestDto(Long id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
