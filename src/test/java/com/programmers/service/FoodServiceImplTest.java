package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.repository.FoodRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FoodServiceImplTest {

    @Autowired
    FoodRepository foodRepository;

    @Test
    @DisplayName("food 생성 테스트")
    void save() {
        Food food = Food.builder()
                .id(1L)
                .price(1000)
                .description("맛있는라면")
                .name("라면")
                .category("noodle")
                .build();

        Food save = foodRepository.save(food);
        Assertions.assertEquals(food.getId(), save.getId());
    }
}