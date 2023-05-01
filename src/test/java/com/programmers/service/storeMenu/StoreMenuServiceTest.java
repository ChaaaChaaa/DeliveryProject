package com.programmers.service.storeMenu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.programmers.domain.food.Food;
import com.programmers.domain.store.Store;
import com.programmers.domain.storeMenu.StoreMenu;
import com.programmers.dto.food.FoodRequestDto;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.dto.storeMenu.StoreMenuRequestDto;
import com.programmers.dto.storeMenu.StoreMenuResponseDto;
import com.programmers.repository.food.FoodRepository;
import com.programmers.repository.store.StoreRepository;
import com.programmers.repository.storeMenu.StoreMenuRepository;
import com.programmers.service.food.FoodService;
import com.programmers.service.store.StoreService;

@SpringBootTest
@Transactional
class StoreMenuServiceTest {

	@Autowired
	FoodRepository foodRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	StoreMenuRepository storeMenuRepository;

	@Autowired
	FoodService foodService;

	@Autowired
	StoreService storeService;

	@Autowired
	StoreMenuService storeMenuService;

	@BeforeEach
	void clean() {
		storeMenuRepository.deleteAll();
		foodRepository.deleteAll();
		storeRepository.deleteAll();
	}

	@Test
	@DisplayName("저장된 메뉴 조회")
	void save() {
		//given
		Food food = basicFoodData();
		FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
		Food savedFood = foodService.save(foodRequestDto);

		Store store = basicStoreData();
		Store savedStore = storeService.save(StoreRequestDto.of(store));

		StoreMenu storeMenu = StoreMenu.builder()
			.food(savedFood)
			.store(savedStore)
			.build();
		//when
		StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);

		// then
		assertEquals(savedStoreMenu.getFood(), savedFood);
		assertEquals(savedStoreMenu.getStore(), savedStore);
	}

	@Test
	@DisplayName("Id로 메뉴 조회")
	void findById() {
		Food food = basicFoodData();
		FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
		Food savedFood = foodService.save(foodRequestDto);

		Store store = basicStoreData();
		Store savedStore = storeService.save(StoreRequestDto.of(store));

		StoreMenu storeMenu = StoreMenu.builder()
			.food(savedFood)
			.store(savedStore)
			.build();

		StoreMenuRequestDto storeMenuRequestDto = StoreMenuRequestDto.of(storeMenu);
		StoreMenu savedStoreMenu = storeMenuService.save(storeMenuRequestDto);

		//when
		StoreMenuResponseDto storeMenuResponseDto = storeMenuService.findById(savedStoreMenu.getStoreMenuId(),
			storeMenuRequestDto);
		assertEquals(savedFood.getName(), storeMenuResponseDto.getFoodName());
		assertEquals(savedStore.getStoreName(), storeMenuResponseDto.getStoreName());
	}

	@Test
	@DisplayName("메뉴 중 가게 수정 테스트")
	void updateStore() {
		Food food = basicFoodData();
		FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
		Food savedFood = foodService.save(foodRequestDto);

		Food updateFood = updateFoodData();
		FoodRequestDto updateFoodRequestDto = FoodRequestDto.of(updateFood);
		Food updatedFood = foodService.save(updateFoodRequestDto);

		Store store = basicStoreData();
		Store savedStore = storeService.save(StoreRequestDto.of(store));

		Store updateStore = updateStoreData();
		Store updatedStore = storeService.save(StoreRequestDto.of(updateStore));

		StoreMenu storeMenu = StoreMenu.builder()
			.food(savedFood)
			.store(savedStore)
			.build();

		StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);
		Long savedMenuId = savedStoreMenu.getStoreMenuId();

		StoreMenu updatedStoreMenu = StoreMenu.builder()
			.food(updatedFood)
			.store(updatedStore)
			.build();

		//when
		storeMenuService.updateStore(savedMenuId, updatedStoreMenu.getStore());

		//then
		StoreMenu targetStoreMenu = storeMenuService.findById(savedMenuId);
		assertEquals(savedMenuId, targetStoreMenu.getStoreMenuId());
		assertEquals(updatedStoreMenu.getStore().getStoreId(), targetStoreMenu.getStore().getStoreId());
	}

	@Test
	@DisplayName("메뉴 중 음식 수정 테스트")
	void updateFood() {
		Food food = basicFoodData();
		FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
		Food savedFood = foodService.save(foodRequestDto);

		Food updateFood = updateFoodData();
		FoodRequestDto updateFoodRequestDto = FoodRequestDto.of(updateFood);
		Food savedUpdatedFood = foodService.save(updateFoodRequestDto);

		Store store = basicStoreData();
		Store savedStore = storeService.save(StoreRequestDto.of(store));

		Store updatedStore = updateStoreData();
		Store savedUpdatedStore = storeService.save(StoreRequestDto.of(updatedStore));

		StoreMenu storeMenu = StoreMenu.builder()
			.food(savedFood)
			.store(savedStore)
			.build();

		StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);
		Long savedMenuId = savedStoreMenu.getStoreMenuId();

		StoreMenu updatedStoreMenu = StoreMenu.builder()
			.food(savedUpdatedFood)
			.store(savedUpdatedStore)
			.build();

		//when
		storeMenuService.updateFood(savedMenuId, updatedStoreMenu.getFood());

		//then
		StoreMenu targetStoreMenu = storeMenuService.findById(savedMenuId);
		assertEquals(savedMenuId, targetStoreMenu.getStoreMenuId());
		assertEquals(updatedStoreMenu.getFood().getId(), targetStoreMenu.getFood().getId());
	}

	@Test
	@DisplayName("메뉴 삭제 테스트")
	void deleteById() {
		Food food = basicFoodData();
		Food savedFood = foodService.save(FoodRequestDto.of(food));

		Store store = basicStoreData();
		Store savedStore = storeService.save(StoreRequestDto.of(store));

		StoreMenu storeMenu = StoreMenu.builder()
			.food(savedFood)
			.store(savedStore)
			.build();

		StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);

		//when
		storeMenuRepository.delete(savedStoreMenu);

		//then
		Optional<StoreMenu> findId = storeMenuRepository.findById(savedStoreMenu.getStoreMenuId());
		Assertions.assertTrue(findId.isEmpty());

	}

	private Food basicFoodData() {
		return Food.builder()
			.price(1000)
			.description("맛있는라면")
			.name("라면")
			.build();
	}

	private Food updateFoodData() {
		return Food.builder()
			.price(2000)
			.description("매콤한라면")
			.name("신라면")
			.build();
	}

	private Store basicStoreData() {
		return Store.builder()
			.category("noodle")
			.storeName("차차네")
			.rating(5.0f)
			.reviewCount(100)
			.build();
	}

	private Store updateStoreData() {
		return Store.builder()
			.category("dumpling")
			.storeName("만두네")
			.rating(4.5f)
			.reviewCount(200)
			.build();
	}
}
