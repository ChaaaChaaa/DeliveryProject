package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.domain.Menu;
import com.programmers.domain.Store;
import com.programmers.dto.menu.MenuResponseDto;
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
        Food savedFood = foodService.save(basicFoodData());
        Store savedStore = storeService.save(basicStoreData());

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();
        //when
        Menu savedMenu =menuRepository.save(menu);

        // then
        assertEquals(savedMenu.getFood(),savedFood);
        assertEquals(savedMenu.getStore(),savedStore);
    }

    @Test
    @DisplayName("Id로 메뉴 조회")
    void findById() {
        Food food = basicFoodData();
        Store store = basicStoreData();

        Food savedFood = foodService.save(food);
        Store savedStore = storeService.save(store);

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        Menu savedMenu =menuRepository.save(menu);

        //when
        MenuResponseDto menuResponseDto = menuService.findById(savedMenu.getMenuId(),menu);
        assertEquals(savedFood.getName(),menuResponseDto.getFoodName());
        assertEquals(savedStore.getStoreName(),menuResponseDto.getStoreName());
    }

    @Test
    @DisplayName("메뉴 수정 테스트")
    void update() {
        Food food = basicFoodData();
        Food savedFood = foodService.save(food);
        Food updatedFood = foodService.save(updateFoodData());

        Store store = basicStoreData();
        Store savedStore = storeService.save(store);
        Store updatedStore = storeService.save(updateStoreData());

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        Menu savedMenu =menuRepository.save(menu);
        Long savedMenuId = savedMenu.getMenuId();


        Menu updatedMenu = Menu.builder()
                .food(updatedFood)
                .store(updatedStore)
                .build();

        //when
        menuService.update(savedMenuId,updatedMenu);

        //then
        Menu targetMenu = menuService.findById(savedMenuId);
        assertEquals(savedMenuId, targetMenu.getMenuId());
        assertEquals(updatedMenu.getFood().getId(), targetMenu.getFood().getId());
        assertEquals(updatedMenu.getStore().getStoreId(), targetMenu.getStore().getStoreId());
    }

    @Test
    @DisplayName("메뉴 삭제 테스트")
    void deleteById() {
        Food savedFood = foodService.save(basicFoodData());
        Store savedStore = storeService.save(basicStoreData());

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        Menu savedMenu =menuRepository.save(menu);

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