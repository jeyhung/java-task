package com.example.restaurant.service.impl;

import com.example.restaurant.dto.OrderCreateDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.dto.OrderListItemDto;
import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.service.OrderService;
import com.example.restaurant.shared.exception.DataNotFoundException;
import com.example.restaurant.shared.exception.WrongParamsException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final MenuService menuService;

    public OrderServiceImpl(ModelMapper modelMapper,
                            OrderRepository orderRepository,
                            MenuService menuService) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.menuService = menuService;
    }

    @Override
    public List<OrderListItemDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(o -> modelMapper.map(o, OrderListItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Order not found"));
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto create(List<OrderCreateDto> items) {
        if (items == null || items.size() == 0) {
            throw new WrongParamsException();
        }

        Order order = Order.builder()
                .totalPrice(BigDecimal.ZERO)
                .build();
        List<OrderItem> orderItems = new ArrayList<>();

        items.forEach(item -> {
            MenuItem menuItem = menuService.getMenuItemById(item.getMenuItemId());

            BigDecimal totalPrice = menuItem.getPrice()
                    .multiply(new BigDecimal(item.getQuantity()));

            order.setTotalPrice(order.getTotalPrice().add(totalPrice));

            orderItems.add(OrderItem.builder()
                    .order(order)
                    .name(menuItem.getName())
                    .quantity(item.getQuantity())
                    .price(menuItem.getPrice())
                    .totalPrice(totalPrice)
                    .build());
        });

        if (orderItems.size() > 0) {
            order.setItems(orderItems);
            order.setCreationAt(LocalDateTime.now());

            orderRepository.save(order);

            return modelMapper.map(order, OrderDto.class);
        }

        return null;
    }
}
