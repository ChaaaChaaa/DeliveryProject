package com.programmers.controller.storeMenu;


import com.programmers.dto.storeMenu.StoreMenuRequestDto;
import com.programmers.dto.storeMenu.StoreMenuResponseDto;
import com.programmers.service.storeMenu.StoreMenuService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/storeMenus")
@RestController
public class StoreMenuController {
    private final StoreMenuService storeMenuService;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveMenu(@RequestBody StoreMenuRequestDto storeMenuRequestDto) {
        storeMenuService.save(storeMenuRequestDto);
    }


    @GetMapping("/{storeMenuId}")
    public StoreMenuResponseDto searchMenuById(@PathVariable Long storeMenuId, @RequestBody StoreMenuRequestDto storeMenuRequestDto) {
        return storeMenuService.findById(storeMenuId, storeMenuRequestDto);
    }

    @PutMapping("/{storeMenuId}/updateFood")
    public void updateFoodInMenu(@PathVariable Long storeMenuId, @RequestBody StoreMenuRequestDto storeMenuRequestDto) {
        storeMenuService.updateFood(storeMenuId, storeMenuRequestDto.getFood());
    }

    @PutMapping("/{storeMenuId}/updateStore")
    public void updateStoreInMenu(@PathVariable Long storeMenuId, @RequestBody StoreMenuRequestDto storeMenuRequestDto) {
        storeMenuService.updateStore(storeMenuId, storeMenuRequestDto.getStore());
    }

    @DeleteMapping("/{storeMenuId}")
    public void deleteMenuId(@PathVariable Long storeMenuId) {
        storeMenuService.deleteById(storeMenuId);
    }
}

