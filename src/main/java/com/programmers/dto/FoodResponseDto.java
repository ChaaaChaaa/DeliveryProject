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
    private String name;
    private String category;
    private int price;
    private String description;
    private String image;

    public FoodResponseDto(Food food) {
        this.name = food.getName();
        this.category = food.getCategory();
        this.price = food.getPrice();
        this.description = food.getDescription();
        this.image = food.getImage();
    }
}
