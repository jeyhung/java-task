package com.example.restaurant.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private long id;
    private BigDecimal totalPrice;
    private List<OrderItemDto> items;
    private LocalDateTime creationAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class OrderItemDto {
        private long id;
        private String name;
        private int quantity;
        private BigDecimal price;
        private BigDecimal totalPrice;
    }
}
