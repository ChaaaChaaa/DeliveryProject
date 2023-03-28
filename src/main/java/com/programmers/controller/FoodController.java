package com.programmers.controller;

import com.programmers.domain.Food;
import com.programmers.dto.food.FoodResponseDto;
import com.programmers.service.food.FoodService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/foods")
@RestController
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/save")
    public void saveFood(@RequestBody Food food) {
        foodService.save(food);
    }


    @GetMapping("/{id}")
    public FoodResponseDto searchFoodById(@PathVariable Long id) {
        return foodService.findById(id);
    }


    @GetMapping("/search")
    public List<FoodResponseDto> searchFoodContainName(@RequestParam("name") String name) {
        return foodService.findByNameContaining(name);
    }

    @PutMapping("/{id}")
    public void updateFood(@PathVariable Long id, @RequestBody Food food) {
        foodService.update(id, food);
    }

    @DeleteMapping("/{id}")
    public void deleteId(@PathVariable Long id) {
        foodService.deleteById(id);
    }
}
