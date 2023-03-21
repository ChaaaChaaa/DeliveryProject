package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.dto.FoodRequestDto;
import com.programmers.dto.FoodResponseDto;

import java.util.List;

public interface FoodService {
    Food save(Food food);

    FoodResponseDto findById(Long id);

    List<FoodResponseDto> findByNameContaining(String Name);

    void update(long id, FoodRequestDto foodRequestDto);

    void delete(Food food);
}
