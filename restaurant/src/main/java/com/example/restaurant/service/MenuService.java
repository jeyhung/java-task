package com.example.restaurant.service;

import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.MenuListItemDto;
import com.example.restaurant.entity.MenuItem;

import java.util.List;

public interface MenuService {
    List<MenuListItemDto> getAll();

    MenuDto getById(long id);

    MenuItem getMenuItemById(long menuItemId);
}
