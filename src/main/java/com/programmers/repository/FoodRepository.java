package com.programmers.repository;

import com.programmers.domain.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    default void createFood(Food food) {
        Food.builder()
                .category(food.getCategory())
                .name(food.getName())
                .price(food.getPrice())
                .description(food.getDescription())
                .image(food.getImage())
                .build();
    }
}
