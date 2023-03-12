package com.programmers.controller;

import com.programmers.domain.Food;
import com.programmers.request.FoodUpdateRequest;
import com.programmers.response.FoodResponse;
import com.programmers.service.FoodService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    //음식 등록
    @PostMapping("/foods")
    public void saveFood(@RequestBody Food food){
        foodService.saveFood(food);
    }

}
