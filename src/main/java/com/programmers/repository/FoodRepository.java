package com.programmers.repository;

import com.programmers.domain.Food;

import java.util.List;

public interface FoodRepository {
    List<Food> getList();
}
