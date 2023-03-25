package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.dto.food.FoodResponseDto;
import com.programmers.dto.food.FoodUpdateRequestDto;
import com.programmers.repository.food.FoodRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;


    public Food save(Food food) {
        return foodRepository.save(food);
    }


    public FoodResponseDto findById(Long id) {
        return FoodResponseDto.of(foodRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 id의 음식이 존재하지 않습니다.")));
    }


    public List<FoodResponseDto> findByNameContaining(String Name) {
        return FoodResponseDto.from(foodRepository.findByNameContaining(Name));
    }


    @Transactional
    public void update(long id, FoodUpdateRequestDto foodUpdateRequestDto) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        food.update(foodUpdateRequestDto.getName(),foodUpdateRequestDto.getPrice(),foodUpdateRequestDto.getDescription());
        //foodRepository.save(foodUpdateRequestDto);
    }

    public void deleteById(long id) {
        foodRepository.deleteById(id);
    }
}
