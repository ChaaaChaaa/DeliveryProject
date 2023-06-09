package com.programmers.repository.food;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.programmers.domain.food.Food;

import lombok.NonNull;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	@NonNull
	Optional<Food> findById(@Nullable Long id);

	List<Food> findByNameContaining(String name);

	Food findByName(String name);

	Food save(@Nullable Food food);

	void delete(@Nullable Food food);
}
