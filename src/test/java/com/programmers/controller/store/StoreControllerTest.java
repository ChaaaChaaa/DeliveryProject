package com.programmers.controller.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmers.domain.store.Store;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.repository.store.StoreRepository;
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
class StoreControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreService storeService;

    @BeforeEach
    void clean() {
        mockMvc = MockMvcBuilders.standaloneSetup(new StoreController(storeService)).build();
        storeRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청시 db에 저장된다.")
    void saveStore() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Store store = basicStoreData();
        Store newStore = storeRepository.save(store);

        String json = objectMapper.writeValueAsString(newStore);

        //when
        mockMvc.perform(post("/stores/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertEquals(1, storeRepository.count());
        Store targetStore = storeRepository.findAll().get(0);
        assertEquals("차차네", targetStore.getStoreName());
        assertEquals("noodle", targetStore.getCategory());
    }


    @Test
    @DisplayName("/get 요청시 db에서 id를 찾아온다.")
    void searchStoreById() throws Exception {
        Store store = basicStoreData();
        Store newStore = storeService.save(StoreRequestDto.of(store));

        //when,then
        mockMvc.perform(get("/stores/{storeId}", newStore.getStoreId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.storeId").value(newStore.getStoreId()))
                .andExpect(jsonPath("$.storeName").value(newStore.getStoreName()))
                .andExpect(jsonPath("$.category").value(newStore.getCategory()));
    }

    @Test
    @DisplayName("/get 요청시 db에서 이름를 찾아온다.")
    void searchStoreByName() throws Exception {
        String storeName = "만두네";
        Store store = dummyStoreData();
        Store newStore = storeService.save(StoreRequestDto.of(store));

        mockMvc.perform(get("/stores/search?storeName={storeName}", storeName))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.storeId").value(newStore.getStoreId()))
                .andExpect(jsonPath("$.storeName").value(newStore.getStoreName()))
                .andExpect(jsonPath("$.category").value(newStore.getCategory()));
    }

    @Test
    @DisplayName("/put 요청시 db에서 가게 내용을 수정한다.")
    void updateStore() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Store store = dummyStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));

        Long updatedStoreId = savedStore.getStoreId();
        String expectedStoreName = "니노네";
        String expectedStoreCategory = "koreanFood";

        Store updatedStore = Store.builder()
                .storeName(expectedStoreName)
                .category(expectedStoreCategory)
                .build();

        mockMvc.perform(put("/stores/{storeId}", updatedStoreId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStore)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/delete 요청시 db에서 가게를 삭제한다.")
    void deleteStoreId() throws Exception {
        Store store = dummyStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));

        mockMvc.perform(delete("/stores/{storeId}", savedStore.getStoreId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        assertEquals(0L, storeRepository.count());
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

    private Store dummyStoreData() {
        return Store.builder()
                .storeId(2L)
                .category("dumpling")
                .storeName("만두네")
                .rating(4.5f)
                .reviewCount(200)
                .build();
    }
}