package com.programmers.controller;

import com.programmers.domain.Food;
import com.programmers.dto.FoodRequestDto;
import com.programmers.service.FoodServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/foods")
public class FoodController {
    private final FoodServiceImpl foodServiceImpl;

    //음식 생성
    @PostMapping
    public void createFood(@RequestBody Food food) {
        foodServiceImpl.create(food);
    }

    //음식 등록
    @PostMapping("/save")
    public void saveFood(@RequestBody Food food) {
        foodServiceImpl.save(food);
    }

    //음식 조회(foodId)
    @GetMapping("/search/id/{foodId}")
    public Optional<Food> searchFoodById(@PathVariable Long foodId) {
        return foodServiceImpl.findById(foodId);
    }

    //음식 조회(포함 단어)
    @GetMapping("/search/{name}")
    public List<Food> searchFoodContainName(@PathVariable String name) {
        return foodServiceImpl.findByNameContaining(name);
    }

    //음식 수정
    @PutMapping("/update/{foodId}")
    public void updateFood(@PathVariable Long foodId, @RequestBody FoodRequestDto foodRequestDto) {
        foodServiceImpl.update(foodId, foodRequestDto);
    }

    //음식 삭제
    @DeleteMapping("/delete")
    public void deleteFoodId(@RequestBody Food food) {
        foodServiceImpl.delete(food);
    }
}
