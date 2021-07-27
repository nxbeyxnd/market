package com.alexeyshekhonov.gbshop.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderDto> items;
    int totalCost;
    int totalCount;

    public CartDto(List<OrderDto> o, int totalCost, int totalCount) {
        this.items = o;
        this.totalCost = totalCost;
        this.totalCount = totalCount;
    }

}
