package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.dto.FoodRequestDto;
import com.programmers.dto.FoodResponseDto;
import com.programmers.repository.FoodRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public FoodResponseDto findById(Long id) {
        return FoodResponseDto.of(foodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 id의 음식이 존재하지 않습니다")));
    }

    @Override
    public List<FoodResponseDto> findByNameContaining(String Name) {
        return FoodResponseDto.from(foodRepository.findByNameContaining(Name));
    }

    @Override
    public void update(long id, FoodRequestDto foodRequestDto) {
        foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        foodRepository.update(foodRequestDto.toEntity());
    }

    @Override
    public void delete(Food food) {
        foodRepository.delete(food);
    }
}
