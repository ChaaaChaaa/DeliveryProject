package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.dto.FoodRequestDto;
import com.programmers.dto.FoodResponseDto;
import com.programmers.repository.FoodRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;

    @Override
    public void create(Food food) {
        foodRepository.createFood(food);
    }

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }


    @Override
    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    }

    @Override
    public List<Food> findByNameContaining(String Name) {
        return foodRepository.findByNameContaining(Name);
    }
}
