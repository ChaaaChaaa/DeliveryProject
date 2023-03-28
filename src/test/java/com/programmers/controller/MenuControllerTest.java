package com.programmers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmers.domain.Food;
import com.programmers.domain.Menu;
import com.programmers.domain.Store;
import com.programmers.dto.menu.MenuRequestDto;
import com.programmers.repository.food.FoodRepository;
import com.programmers.repository.menu.MenuRepository;
import com.programmers.repository.store.StoreRepository;
import com.programmers.service.food.FoodService;
import com.programmers.service.menu.MenuService;
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

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MenuControllerTest {
    private MockMvc mockMvc;
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
    void clean(){
        mockMvc = MockMvcBuilders.standaloneSetup(new MenuController(menuService)).build();
        menuRepository.deleteAll();
        foodRepository.deleteAll();
        storeRepository.deleteAll();
    }


    @Test
    @DisplayName("/post 요청시 db에 저장이 된다.")
    void saveMenu() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        Food savedFood = foodService.save(basicFoodData());
        Store savedStore = storeService.save(basicStoreData());

        //when
        MenuRequestDto menuRequestDto = MenuRequestDto.builder()
                .foodName(savedFood.getName())
                .storeName(savedStore.getStoreName())
                .build();
        String json = objectMapper.writeValueAsString(menuRequestDto);

        //then
        mockMvc.perform(post("/menu/save")
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
        Food savedFood = foodService.save(basicFoodData());
        Store savedStore = storeService.save(basicStoreData());
        ObjectMapper objectMapper = new ObjectMapper();

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        Menu savedMenu = menuRepository.save(menu);
        String json = objectMapper.writeValueAsString(menu);

        //when,then
        mockMvc.perform(get("/menu/{menuId}",savedMenu.getMenuId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/put 요청시 db에서 메뉴 내용을 수정한다.")
    void updateMenu() throws Exception {
        //given
        Food savedFood = foodService.save(basicFoodData());
        Store savedStore = storeService.save(basicStoreData());
        ObjectMapper objectMapper = new ObjectMapper();

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        menuRepository.save(menu);

        //when
        Food updateFood = foodService.save(updateFoodData());
        Store updateStore = storeService.save(updateStoreData());

        Menu updateMenu = Menu.builder()
                .food(updateFood)
                .store(updateStore)
                .build();

        Menu savedMenu = menuRepository.save(updateMenu);
        String json = objectMapper.writeValueAsString(menu);

        //then
        mockMvc.perform(put("/menu/{menuId}",savedMenu.getMenuId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/delete 요청시 db에서 메뉴를 삭제한다.")
    void deleteMenuId() throws Exception {
        //given
        Food savedFood = foodService.save(basicFoodData());
        Store savedStore = storeService.save(basicStoreData());
        ObjectMapper objectMapper = new ObjectMapper();

        Menu menu = Menu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();

        Menu savedMenu = menuRepository.save(menu);
        String json = objectMapper.writeValueAsString(menu);

        //when,then
        mockMvc.perform(delete("/menu/{menuId}",savedMenu.getMenuId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
        assertEquals(0L, menuRepository.count());
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