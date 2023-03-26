package com.programmers.repository.food;

import com.programmers.domain.Food;

import java.util.HashMap;
import java.util.Map;

public class FoodMapRepository {
    private final Map<Long, Food> hashMap;

    public FoodMapRepository(HashMap<Long, Food> hashMap) {
        this.hashMap = hashMap;
    }
}
