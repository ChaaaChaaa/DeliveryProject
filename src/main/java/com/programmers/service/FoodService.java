package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.dto.FoodRequestDto;
import com.programmers.dto.FoodResponseDto;
import com.programmers.request.FoodUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface FoodService {
    void create(Food food);
    Food save(Food food);
    Optional<Food> findById(Long id);
    List<Food> findByNameContaining(String Name);
    FoodResponseDto update(long id, FoodRequestDto foodRequestDto);
    void delete(Food food);
}
