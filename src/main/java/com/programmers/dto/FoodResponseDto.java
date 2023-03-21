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

    public static FoodResponseDto of(Food food) {
        return FoodResponseDto.builder()
                .id(food.getId())
                .category(food.getCategory())
                .name(food.getName())
                .price(food.getPrice())
                .description(food.getDescription())
                .image(food.getImage())
                .build();
    }


    public static List<FoodResponseDto> from(List<Food> foods) {
        return foods.stream()
                .map(food -> FoodResponseDto.builder()
                        .id(food.getId())
                        .name(food.getName())
                        .category(food.getCategory())
                        .price(food.getPrice())
                        .description(food.getDescription())
                        .image(food.getImage())
                        .build())
                .collect(Collectors.toList());
    }
}
