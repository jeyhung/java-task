package com.example.restaurant.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private long id;
    private String name;
    private List<MenuItemDto> items;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class MenuItemDto {
        private long id;
        private String name;
        private BigDecimal price;
    }
}
