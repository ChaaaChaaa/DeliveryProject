package com.programmers.controller;


import com.programmers.dto.menu.MenuRequestDto;
import com.programmers.dto.menu.MenuResponseDto;
import com.programmers.service.MenuService;

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
@RequestMapping("/menus")
@RestController
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/save")
    public void saveMenu(@RequestBody MenuRequestDto menuRequestDto) {
        menuService.save(menuRequestDto);
    }


    @GetMapping("/{menuId}")
    public MenuResponseDto searchMenuById(@PathVariable Long menuId) {
        return menuService.findById(menuId);
    }


    @PutMapping("/{menuId}")
    public void updateMenu(@PathVariable Long menuId, @RequestBody MenuUpdateRequestDto menuUpdateRequestDto) {
        menuService.update(menuId, menuUpdateRequestDto);
    }

    @DeleteMapping("/{menuId}")
    public void deleteMenuId(@PathVariable Long menuId) {
        menuService.deleteById(menuId);
    }
}

