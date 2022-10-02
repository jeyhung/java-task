package com.example.restaurant.service;

import com.example.restaurant.dto.OrderCreateDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.dto.OrderListItemDto;

import java.util.List;

public interface OrderService {
    List<OrderListItemDto> getAll();

    OrderDto getById(long id);

    OrderDto create(List<OrderCreateDto> items);
}
