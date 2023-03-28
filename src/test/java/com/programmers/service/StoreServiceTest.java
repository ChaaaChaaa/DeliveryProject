package com.programmers.service;

import com.programmers.domain.Store;
import com.programmers.dto.store.StoreResponseDto;
import com.programmers.repository.store.StoreRepository;
import com.programmers.service.store.StoreService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreServiceTest {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    StoreService storeService;

    @BeforeEach
    void clean(){
        storeRepository.deleteAll();
    }

    @Test
    @DisplayName("저장된 가게 조회")
    void save() {
        Store store = basicStoreData();
        Store newStore = storeRepository.save(store);
        assertEquals(newStore.getStoreName(), store.getStoreName());
    }

    @Test
    @DisplayName("Id로 가게 조회")
    void findByStoreId() {
        Store store = basicStoreData();
        Store newStore = storeService.save(store);

        StoreResponseDto storeResponseDto = storeService.findByStoreId(newStore.getStoreId());

        assertNotNull(storeResponseDto);
        assertEquals("차차네",newStore.getStoreName());
        assertEquals("noodle",newStore.getCategory());
    }

    @Test
    @DisplayName("Id 조회 실패")
    void findNonExistingStoreById() {
        Store store = basicStoreData();
        Store newStore = storeService.save(store);
        Long nonExistingId = newStore.getStoreId() + 100;

        //when & then
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> storeService.findByStoreId(nonExistingId));
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
    }

    @Test
    void findByStoreName() {
        //given
        Store store = basicStoreData();
        Store newStore = storeService.save(store);

        //when
        StoreResponseDto targetStore = storeService.findByStoreName(newStore.getStoreName());

        //then
        Assertions.assertEquals(targetStore.getStoreName(), newStore.getStoreName());
    }

    @Test
    void update() {
        //given
        String modifiedStoreName = "니노네";
        String modifiedStoreCategory = "koreanFood";

        Store store = storeRepository.save(dummyStoreData());
        Long storeId = store.getStoreId();

        Store updatedStore = Store.builder()
                .storeName(modifiedStoreName)
                .category(modifiedStoreCategory)
                .build();

        //when
        storeService.update(storeId,updatedStore);

        //then
        Store targetStore = storeRepository.findById(storeId).orElseThrow();

        assertEquals(modifiedStoreName,targetStore.getStoreName());
        assertEquals(modifiedStoreCategory, targetStore.getCategory());
    }

    @Test
    void deleteById() {
        Store store = basicStoreData();
        Store newStore = storeRepository.save(store);

        storeRepository.delete(newStore);

        Optional<Store> findId = storeRepository.findByStoreId(newStore.getStoreId());
        Assertions.assertTrue(findId.isEmpty());
    }

    private Store basicStoreData(){
        return Store.builder()
                .storeId(1L)
                .category("noodle")
                .storeName("차차네")
                .rating(5.0f)
                .reviewCount(100)
                .build();
    }

    private Store dummyStoreData(){
        return Store.builder()
                .storeId(2L)
                .category("dumpling")
                .storeName("만두네")
                .rating(4.5f)
                .reviewCount(200)
                .build();
    }
}