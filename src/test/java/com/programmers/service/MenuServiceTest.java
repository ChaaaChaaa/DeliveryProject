package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.domain.Menu;
import com.programmers.domain.Store;
import com.programmers.dto.food.FoodRequestDto;
import com.programmers.dto.menu.MenuRequestDto;
import com.programmers.dto.menu.MenuResponseDto;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.repository.food.FoodRepository;
import com.programmers.repository.menu.MenuRepository;
import com.programmers.repository.store.StoreRepository;
import com.programmers.service.food.FoodService;
import com.programmers.service.menu.MenuService;
import com.programmers.service.store.StoreService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    FoodService foodService;

    @Autowired
    StoreService storeService;

    @Autowired
    MenuService menuService;

    @BeforeEach
    void clean() {
        menuRepository.deleteAll();
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

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();
        //when
        Menu savedMenu = menuRepository.save(menu);

        // then
        assertEquals(savedMenu.getFood(), savedFood);
        assertEquals(savedMenu.getStore(), savedStore);
    }

    @Test
    @DisplayName("Id로 메뉴 조회")
    void findById() {
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food savedFood = foodService.save(foodRequestDto);

        Store store = basicStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        MenuRequestDto menuRequestDto = MenuRequestDto.of(menu);
        Menu savedMenu = menuRepository.save(menu);

        //when
        MenuResponseDto menuResponseDto = menuService.findById(savedMenu.getMenuId(), menuRequestDto);
        assertEquals(savedFood.getName(), menuResponseDto.getFoodName());
        assertEquals(savedStore.getStoreName(), menuResponseDto.getStoreName());
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

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        Menu savedMenu = menuRepository.save(menu);
        Long savedMenuId = savedMenu.getMenuId();


        Menu updatedMenu = Menu.builder()
                .food(updatedFood)
                .store(updatedStore)
                .build();

        //when
        menuService.updateStore(savedMenuId, updatedMenu.getStore());

        //then
        Menu targetMenu = menuService.findById(savedMenuId);
        assertEquals(savedMenuId, targetMenu.getMenuId());
        assertEquals(updatedMenu.getStore().getStoreId(), targetMenu.getStore().getStoreId());
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

        Store updatedStore= updateStoreData();
        Store savedUpdatedStore = storeService.save(StoreRequestDto.of(updatedStore));

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        Menu savedMenu = menuRepository.save(menu);
        Long savedMenuId = savedMenu.getMenuId();


        Menu updatedMenu = Menu.builder()
                .food(savedUpdatedFood)
                .store(savedUpdatedStore)
                .build();

        //when
        menuService.updateFood(savedMenuId, updatedMenu.getFood());

        //then
        Menu targetMenu = menuService.findById(savedMenuId);
        assertEquals(savedMenuId, targetMenu.getMenuId());
        assertEquals(updatedMenu.getFood().getId(), targetMenu.getFood().getId());
    }

    @Test
    @DisplayName("메뉴 삭제 테스트")
    void deleteById() {
        Food food = basicFoodData();
        Food savedFood = foodService.save(FoodRequestDto.of(food));

        Store store = basicStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        Menu savedMenu = menuRepository.save(menu);

        //when
        menuRepository.delete(savedMenu);

        //then
        Optional<Menu> findId = menuRepository.findById(savedMenu.getMenuId());
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

    private Food updateFoodData() {
        return Food.builder()
                .id(2L)
                .price(2000)
                .description("매콤한라면")
                .name("신라면")
                .build();
    }

    private Store basicStoreData() {
        return Store.builder()
                .storeId(1L)
                .category("noodle")
                .storeName("차차네")
                .rating(5.0f)
                .reviewCount(100)
                .build();
    }

    private Store updateStoreData() {
        return Store.builder()
                .storeId(2L)
                .category("dumpling")
                .storeName("만두네")
                .rating(4.5f)
                .reviewCount(200)
                .build();
    }
}