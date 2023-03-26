package com.programmers.dto.food;

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
    private final int price;
    private final String description;
    private final String image;

    @Builder
    public FoodResponseDto(Long id, String name, int price, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public static FoodResponseDto of(Food food) {
        return FoodResponseDto.builder()
                .id(food.getId())
                .name(food.getName())
                .price(food.getPrice())
                .description(food.getDescription())
                .image(food.getImage())
                .build();
    }


    public static List<FoodResponseDto> from(List<Food> foods) {
        return foods.stream()
                .map(FoodResponseDto::of)
                .collect(Collectors.toList());
    }
}
