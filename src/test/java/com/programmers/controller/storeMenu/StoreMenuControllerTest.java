package com.programmers.controller.storeMenu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmers.domain.food.Food;
import com.programmers.domain.storeMenu.StoreMenu;
import com.programmers.domain.store.Store;
import com.programmers.dto.food.FoodRequestDto;
import com.programmers.dto.storeMenu.StoreMenuRequestDto;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.repository.food.FoodRepository;
import com.programmers.repository.storeMenu.StoreMenuRepository;
import com.programmers.repository.store.StoreRepository;
import com.programmers.service.food.FoodService;
import com.programmers.service.storeMenu.StoreMenuService;
import com.programmers.service.store.StoreService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class StoreMenuControllerTest {
    private MockMvc mockMvc;
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
        mockMvc = MockMvcBuilders.standaloneSetup(new StoreMenuController(storeMenuService)).build();
        storeMenuRepository.deleteAll();
        foodRepository.deleteAll();
        storeRepository.deleteAll();
    }


    @Test
    @DisplayName("/post 요청시 db에 저장이 된다.")
    void saveMenu() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);

        Food savedFood = foodService.save(foodRequestDto);
        Store store = basicStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));

        //when
        StoreMenuRequestDto storeMenuRequestDto = StoreMenuRequestDto.builder()
                .foodName(savedFood.getName())
                .storeName(savedStore.getStoreName())
                .build();
        String json = objectMapper.writeValueAsString(storeMenuRequestDto);

        //then
        mockMvc.perform(post("/storeMenus/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/get 요청시 db에서 id를 찾아온다.")
    void searchMenuById() throws Exception {
        //given
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food savedFood = foodService.save(foodRequestDto);
        Store store = basicStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));
        ObjectMapper objectMapper = new ObjectMapper();

        StoreMenu storeMenu = StoreMenu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);
        String json = objectMapper.writeValueAsString(storeMenu);

        //when,then
        mockMvc.perform(get("/storeMenus/{storeMenuId}", savedStoreMenu.getStoreMenuId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/put 요청시 db에서 메뉴 가게 내용을 수정한다.")
    void updateStoreMenu() throws Exception {
        //given
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food savedFood = foodService.save(foodRequestDto);
        Store store = basicStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));
        ObjectMapper objectMapper = new ObjectMapper();

        StoreMenu storeMenu = StoreMenu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        storeMenuRepository.save(storeMenu);

        //when
        Food updateFood = updateFoodData();
        FoodRequestDto updateFoodRequestDto = FoodRequestDto.of(updateFood);
        Food updateSavedFood = foodService.save(updateFoodRequestDto);

        Store updateStore = updateStoreData();
        StoreRequestDto storeRequestDto = StoreRequestDto.of(updateStore);
        Store updateSavedStore = storeService.save(storeRequestDto);

        StoreMenu updateStoreMenu = StoreMenu.builder()
                .food(updateSavedFood)
                .store(updateSavedStore)
                .build();

        StoreMenu savedStoreMenu = storeMenuRepository.save(updateStoreMenu);
        String json = objectMapper.writeValueAsString(storeMenu);

        //then
        mockMvc.perform(put("/storeMenus/{storeMenuId}/updateStore", savedStoreMenu.getStoreMenuId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/put 요청시 db에서 메뉴 음식 내용을 수정한다.")
    void updateFoodMenu() throws Exception {
        //given
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food savedFood = foodService.save(foodRequestDto);

        Store store = basicStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));
        ObjectMapper objectMapper = new ObjectMapper();

        StoreMenu storeMenu = StoreMenu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        storeMenuRepository.save(storeMenu);

        //when
        Food updateFood = updateFoodData();
        FoodRequestDto updateFoodRequestDto = FoodRequestDto.of(updateFood);
        Food updateSavedFood = foodService.save(updateFoodRequestDto);

        Store updateStore = updateStoreData();
        StoreRequestDto storeRequestDto = StoreRequestDto.of(updateStore);
        Store updateSavedStore = storeService.save(storeRequestDto);

        StoreMenu updateStoreMenu = StoreMenu.builder()
                .food(updateSavedFood)
                .store(updateSavedStore)
                .build();

        StoreMenu savedStoreMenu = storeMenuRepository.save(updateStoreMenu);
        String json = objectMapper.writeValueAsString(storeMenu);

        //then
        mockMvc.perform(put("/storeMenus/{storeMenuId}/updateFood", savedStoreMenu.getStoreMenuId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/delete 요청시 db에서 메뉴를 삭제한다.")
    void deleteStoreMenuId() throws Exception {
        //given
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food savedFood = foodService.save(foodRequestDto);

        Store store = basicStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));
        ObjectMapper objectMapper = new ObjectMapper();

        StoreMenu storeMenu = StoreMenu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);
        String json = objectMapper.writeValueAsString(storeMenu);

        //when,then
        mockMvc.perform(delete("/storeMenus/{storeMenuId}", savedStoreMenu.getStoreMenuId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
        assertEquals(0L, storeMenuRepository.count());
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
