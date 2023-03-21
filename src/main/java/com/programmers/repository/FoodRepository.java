package com.programmers.repository;

import com.programmers.domain.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.NonNull;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @NonNull
    Optional<Food> findById(@Nullable Long id);

    List<Food> findByNameContaining(String Name);

    Food save(@Nullable Food food);


    @Transactional
    @Modifying
    @Query("UPDATE Food f SET f.name = :#{#food.name}, f.category = :#{#food.category}, f.price = :#{#food.price}, f.description = :#{#food.description}, f.image = :#{#food.image} WHERE f.id = :#{#food.id}")
    void update(@Param("food") Food food);

    void delete(@Nullable Food food);
}
