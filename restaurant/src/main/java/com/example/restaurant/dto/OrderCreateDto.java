package com.example.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {
    @Min(1)
    private long menuItemId;

    @Min(1)
    private int quantity;
}
