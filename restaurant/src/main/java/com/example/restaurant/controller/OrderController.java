package com.example.restaurant.controller;

import com.example.restaurant.dto.*;
import com.example.restaurant.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "orders/")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderListItemDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping(value = "/{id}")
    public OrderDto getById(@PathVariable long id) {
        return orderService.getById(id);
    }

    @PostMapping
    public OrderDto create(@RequestBody List<OrderCreateDto> items) {
        return orderService.create(items);
    }
}