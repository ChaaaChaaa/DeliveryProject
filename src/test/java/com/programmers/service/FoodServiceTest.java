package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.dto.FoodResponseDto;
import com.programmers.repository.FoodRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
        assertEquals(food.getId(), newFood.getId());
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
        assertThrows(EntityNotFoundException.class, () -> foodService.findById(nonExistingId));
    }

    @Test
    @DisplayName("이름으로 음식 조회")
    void findByNameContaining() {
        //given
        Food requestFood = dummyFoodData();
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
        String modifiedName = "비빔라면";
        int modifiedPrice = 3000;
        Food dummyFood = foodRepository.save(dummyFoodData());
        Long dummyFoodId = dummyFood.getId();

        Food food = Food.builder()
                .id(dummyFood.getId())
                .price(modifiedPrice)
                .description(dummyFood.getDescription())
                .name(modifiedName)
                .category(dummyFood.getCategory())
                .build();

        //when
        foodRepository.save(food);

        //then
        Food findFood = foodRepository.findById(dummyFoodId).orElseThrow();

        assertEquals(dummyFoodId, findFood.getId());
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
                .category("noodle")
                .build();
    }

    private Food dummyFoodData() {
        return Food.builder()
                .id(2L)
                .price(2000)
                .description("매콤한라면")
                .name("신라면")
                .category("noodle")
                .build();
    }

}