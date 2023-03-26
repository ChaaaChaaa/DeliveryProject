package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.dto.food.FoodResponseDto;
import com.programmers.dto.food.FoodUpdateRequestDto;
import com.programmers.repository.food.FoodRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FoodServiceTest {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodService foodService;

    @BeforeEach
    void clean(){
        foodRepository.deleteAll();
    }

    @Test
    @DisplayName("save test")
    void save() {
        Food food = basicFoodData();
        Food newFood = foodRepository.save(food);
        assertEquals(food.getPrice(), newFood.getPrice());
    }

    @Test
    @DisplayName("Id로 음식 조회")
    void findById() {
        //given
        Food requestFood = basicFoodData();
        Food newFood = foodService.save(requestFood);

        //when
        FoodResponseDto foodResponseDto = foodService.findById(newFood.getId());

        //then
        assertNotNull(foodResponseDto);
        assertEquals("라면", newFood.getName());
        assertEquals(1000, newFood.getPrice());
    }

    @Test
    @DisplayName("Id 조회 실패")
    void findByIdFail() {
        //given
        Food requestFood = basicFoodData();
        Food newFood = foodService.save(requestFood);
        Long nonExistingId = newFood.getId() + 100;

        //when & then
        assertThrows(ResponseStatusException.class, () -> foodService.findById(nonExistingId));
    }

    @Test
    @DisplayName("이름으로 음식 조회")
    void findByNameContaining() {
        //given
        Food requestFood = basicFoodData();
        Food newFood = foodRepository.save(requestFood);

        //when
        List<FoodResponseDto> foods = foodService.findByNameContaining(newFood.getName());

        //then
        assertNotNull(foods);
        Assertions.assertEquals(1, foods.size());
    }


    @Test
    @DisplayName("update test")
    void update() {
        //given
        String modifiedName = "냉면";
        int modifiedPrice = 3000;
        String modifiedDescription ="시뭔한 냉면";

        Food dummyFood = foodRepository.save(dummyFoodData());
        Long dummyId = dummyFood.getId();

        FoodUpdateRequestDto foodUpdateRequestDto = FoodUpdateRequestDto.builder()
                .name(modifiedName)
                .price(modifiedPrice)
                .description(modifiedDescription)
                .build();

        //when
        foodService.update(dummyId,foodUpdateRequestDto);

        //then
        Food findFood = foodRepository.findById(dummyId).orElseThrow();

        assertEquals(dummyId, findFood.getId());
        assertEquals(modifiedName, findFood.getName());
        assertEquals(modifiedPrice, findFood.getPrice());
    }

    @Test
    @DisplayName("delete")
    void delete() {
        //given
        Food food = basicFoodData();
        Food newFood = foodRepository.save(food);

        //when
        foodRepository.delete(newFood);

        //then
        Optional<Food> findId = foodRepository.findById(newFood.getId());
        Assertions.assertTrue(findId.isEmpty());
    }

    private Food basicFoodData() {
        return Food.builder()
                .id(1L)
                .price(1000)
                .description("맛있는라면")
                .name("라면")
                .build();
    }

    private Food dummyFoodData() {
        return Food.builder()
                .id(2L)
                .price(2000)
                .description("매콤한라면")
                .name("신라면")
                .build();
    }

}