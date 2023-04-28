package com.programmers.service.storeMenu;

import com.programmers.domain.food.Food;
import com.programmers.domain.storeMenu.StoreMenu;
import com.programmers.domain.store.Store;

import com.programmers.dto.storeMenu.StoreMenuRequestDto;
import com.programmers.dto.storeMenu.StoreMenuResponseDto;
import com.programmers.exception.food.FoodNotFoundException;
import com.programmers.exception.menu.MenuNotFoundException;
import com.programmers.exception.store.StoreNotFoundException;
import com.programmers.repository.food.FoodRepository;
import com.programmers.repository.storeMenu.StoreMenuRepository;
import com.programmers.repository.store.StoreRepository;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StoreMenuService {
    private final StoreMenuRepository storeMenuRepository;
    private final FoodRepository foodRepository;
    private final StoreRepository storeRepository;

    public StoreMenu save(StoreMenuRequestDto storeMenuRequestDto) {
        Store store = storeRepository.findByStoreName(storeMenuRequestDto.getStoreName());
        if(store == null) {
            throw new StoreNotFoundException();
        }

        Food food = foodRepository.findByName(storeMenuRequestDto.getFoodName());
        if(food == null) {
            throw new FoodNotFoundException();
        }

        StoreMenu savedStoreMenu = StoreMenu.builder()
                .food(food)
                .store(store)
                .build();
        return storeMenuRepository.save(savedStoreMenu);
    }


    @Transactional
    public StoreMenuResponseDto findById(Long id, StoreMenuRequestDto storeMenuRequestDto) {
        StoreMenu updateStoreMenu = storeMenuRepository.findById(id).orElseThrow(MenuNotFoundException::new);
        Food food = foodRepository.findById(storeMenuRequestDto.getFood().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 음식이 존재하지 않습니다."));
        Store store = storeRepository.findById(storeMenuRequestDto.getStore().getStoreId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 가게가 존재하지 않습니다."));

        updateStoreMenu.changeStore(store);
        updateStoreMenu.changeFood(food);

        return StoreMenuResponseDto.convertIdToName(storeMenuRepository.save(updateStoreMenu));
    }

    @Transactional
    public void updateFood(long id, Food food) {
        StoreMenu storeMenu = storeMenuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));

        storeMenu.changeFood(food);
        storeMenuRepository.save(storeMenu);
    }
    @Transactional
    public void updateStore(long id, Store store) {
        StoreMenu storeMenu = storeMenuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));

        storeMenu.changeStore(store);
        storeMenuRepository.save(storeMenu);
    }



    public StoreMenu findById(Long id) {
        return storeMenuRepository.findById(id).orElseThrow(MenuNotFoundException::new);
    }

    public void deleteById(long id) {
        storeMenuRepository.deleteById(id);
    }
}
