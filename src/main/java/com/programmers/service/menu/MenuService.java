package com.programmers.service.menu;

import com.programmers.domain.Food;
import com.programmers.domain.Menu;
import com.programmers.domain.Store;

import com.programmers.dto.menu.MenuRequestDto;
import com.programmers.dto.menu.MenuResponseDto;
import com.programmers.exception.FoodNotFoundException;
import com.programmers.exception.MenuNotFoundException;
import com.programmers.exception.StoreNotFoundException;
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
        Store store = storeRepository.findByStoreName(menuRequestDto.getStoreName());
        if(store == null) {
            throw new StoreNotFoundException();
        }

        Food food = foodRepository.findByName(menuRequestDto.getFoodName());
        if(food == null) {
            throw new FoodNotFoundException();
        }

        Menu savedMenu = Menu.builder()
                .food(food)
                .store(store)
                .build();
        return menuRepository.save(savedMenu);
    }


    @Transactional
    public MenuResponseDto findById(Long id,Menu menu) {
        Menu updateMenu = menuRepository.findById(id).orElseThrow(MenuNotFoundException::new);
        Food food = foodRepository.findById(menu.getFood().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 음식이 존재하지 않습니다."));
        Store store = storeRepository.findById(menu.getStore().getStoreId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 가게가 존재하지 않습니다."));

        updateMenu.changeStore(store);
        updateMenu.changeFood(food);

        return MenuResponseDto.convertIdToName(menuRepository.save(updateMenu));
    }

    @Transactional
    public void update(long id, Menu menu) {
        Menu updatedMenu = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));
        updatedMenu.changeFood(menu.getFood());
        updatedMenu.changeStore(menu.getStore());
    }

    public Menu findById(Long id) {
        return menuRepository.findById(id).orElseThrow(MenuNotFoundException::new);
    }

    public void deleteById(long id) {
        menuRepository.deleteById(id);
    }
}
