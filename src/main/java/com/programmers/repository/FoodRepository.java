package com.programmers.repository;

import com.programmers.domain.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import lombok.NonNull;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @NonNull
    Optional<Food> findById(Long id);

    List<Food> findByNameContaining(String Name);

    Food save(Food food);

    void delete(Food food);
}
