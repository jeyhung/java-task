package com.example.restaurant.controller;

import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.MenuListItemDto;
import com.example.restaurant.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "menus/")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuListItemDto> getAll() {
        return menuService.getAll();
    }

    @GetMapping(value = "/{id}")
    public MenuDto getById(@PathVariable long id) {
        return menuService.getById(id);
    }
}