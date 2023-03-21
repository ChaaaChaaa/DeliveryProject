package com.programmers.dto;

import com.programmers.domain.Food;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class FoodResponseDto {
    private final Long id;
    private final String name;
    private final String category;
    private final int price;
    private final String description;
    private final String image;

    @Builder
    public FoodResponseDto(Long id, String name, String category, int price, String description, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public FoodResponseDto(Food food) {
        this.name = food.getName();
        this.category = food.getCategory();
        this.price = food.getPrice();
        this.description = food.getDescription();
        this.image = food.getImage();
    }
}
