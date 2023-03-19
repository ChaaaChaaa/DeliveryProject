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
}
