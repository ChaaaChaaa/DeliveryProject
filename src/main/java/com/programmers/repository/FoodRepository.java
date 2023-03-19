package com.programmers.repository;

import com.programmers.domain.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    Food save(Food food);
    Optional<Food> findById(Long id);



}
