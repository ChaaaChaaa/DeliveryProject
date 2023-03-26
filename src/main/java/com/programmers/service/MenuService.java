package com.programmers.service;

import com.programmers.domain.Food;
import com.programmers.domain.Menu;
import com.programmers.domain.Store;

import com.programmers.dto.menu.MenuRequestDto;
import com.programmers.dto.menu.MenuResponseDto;
import com.programmers.repository.food.FoodRepository;
import com.programmers.repository.menu.MenuRepository;
import com.programmers.repository.store.StoreRepository;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;
    private final StoreRepository storeRepository;

    public Menu save(MenuRequestDto menuRequestDto) {
        Menu savedMenu = Menu.builder()
                .food(foodRepository.findByName(menuRequestDto.getFoodName()))
                .store(storeRepository.findByStoreName(menuRequestDto.getStoreName()))
                .build();
        return menuRepository.save(savedMenu);
    }


    public MenuResponseDto findById(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        Food food = foodRepository.findById(menu.getFood().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 음식이 존재하지 않습니다."));
        Store store = storeRepository.findById(menu.getStore().getStoreId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 가게가 존재하지 않습니다."));

        return MenuResponseDto.builder()
                .storeName(store.getStoreName())
                .foodName(food.getName())
                .build();
    }


    @Transactional
    public void update(long id, MenuUpdateRequestDto menuUpdateRequestDto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Food food = foodRepository.findById(menuUpdateRequestDto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "food가 존재하지 않습니다."));
        Store store = storeRepository.findById(menuUpdateRequestDto.getStoreId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "store가 존재하지 않습니다."));
        menu.update(store, food);
    }

    public void deleteById(long id) {
        menuRepository.deleteById(id);
    }
}
