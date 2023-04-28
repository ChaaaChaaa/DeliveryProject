package com.programmers.service.food;

import com.programmers.domain.food.Food;
import com.programmers.dto.food.FoodRequestDto;
import com.programmers.dto.food.FoodResponseDto;
import com.programmers.repository.food.FoodRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Transactional
    public Food save(FoodRequestDto foodRequestDto) {
        return foodRepository.save(foodRequestDto.toEntity());
    }


    public FoodResponseDto findById(Long foodId) {
        return FoodResponseDto.of(foodRepository.findById(foodId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 음식이 존재하지 않습니다.")));
    }


    public List<FoodResponseDto> findByNameContaining(String name) {
        return FoodResponseDto.from(foodRepository.findByNameContaining(name));
    }


    @Transactional
    public void update(long id, FoodRequestDto foodRequestDto) {
        Food updatedFood = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 음식이 존재하지 않습니다."));
        updatedFood.changeName(foodRequestDto.getName());
        updatedFood.changePrice(foodRequestDto.getPrice());
        updatedFood.changeDescription(foodRequestDto.getDescription());
        foodRepository.save(updatedFood);
    }

    public void deleteById(long id) {
        foodRepository.deleteById(id);
    }
}
