package com.programmers.service.food;

import com.programmers.domain.food.Food;
import com.programmers.dto.food.FoodRequestDto;
import com.programmers.dto.food.FoodResponseDto;
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
    void clean() {
        foodRepository.deleteAll();
    }

    @Test
    @DisplayName("저장된 음식 조회")
    void save() {
        Food food = basicFoodData();
        Food newFood = foodRepository.save(food);
        assertEquals(food.getPrice(), newFood.getPrice());
    }

    @Test
    @DisplayName("Id로 음식 조회")
    void findById() {
        //given
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food newFood = foodService.save(foodRequestDto);

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
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food newFood = foodService.save(foodRequestDto);
        Long nonExistingId = newFood.getId() + 100;

        //when & then
        assertThrows(ResponseStatusException.class, () -> foodService.findById(nonExistingId));
    }

    @Test
    @DisplayName("이름으로 음식 조회")
    void findByNameContaining() {
        //given
        Food food = basicFoodData();

        Food newFood = foodRepository.save(food);

        //when
        List<FoodResponseDto> foods = foodService.findByNameContaining(newFood.getName());

        //then
        assertNotNull(foods);
        Assertions.assertEquals(1, foods.size());
    }


    @Test
    @DisplayName("음식 수정 테스트")
    void update() {
        //given
        String modifiedName = "냉면";
        int modifiedPrice = 3000;
        String modifiedDescription = "시뭔한 냉면";

        Food dummyFood = foodRepository.save(dummyFoodData());
        Long dummyFoodId = dummyFood.getId();

        Food updatedFood = Food.builder()
                .name(modifiedName)
                .price(modifiedPrice)
                .description(modifiedDescription)
                .build();

        FoodRequestDto foodRequestDto = FoodRequestDto.of(updatedFood);

        //when
        foodService.update(dummyFoodId, foodRequestDto);

        //then
        Food targetFood = foodRepository.findById(dummyFoodId).orElseThrow();
        assertEquals(dummyFoodId, targetFood.getId());
        assertEquals(modifiedName, targetFood.getName());
        assertEquals(modifiedPrice, targetFood.getPrice());
    }

    @Test
    @DisplayName("음식 삭제 테스트")
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
