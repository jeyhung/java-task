package com.example.restaurant.service.impl;

import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.MenuListItemDto;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.shared.exception.DataNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private final ModelMapper modelMapper;
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuServiceImpl(ModelMapper modelMapper,
                           MenuRepository menuRepository,
                           MenuItemRepository menuItemRepository) {
        this.modelMapper = modelMapper;
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuListItemDto> getAll() {
        return menuRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, MenuListItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MenuDto getById(long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Menu not found!"));
        return modelMapper.map(menu, MenuDto.class);
    }

    @Override
    public MenuItem getMenuItemById(long menuItemId) {
        return menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new DataNotFoundException("Menu item not found"));
    }
}
