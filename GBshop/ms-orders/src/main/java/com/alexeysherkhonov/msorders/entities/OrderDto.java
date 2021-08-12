package com.alexeysherkhonov.msorders.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private String productTitle;
    private int count;
    private int costPerProduct;
    private int cost;

    public OrderDto(Order o) {
        this.productTitle = o.getTitle();
        this.count = o.getCount();
        this.costPerProduct = o.getCostPerItem();
        this.cost = o.getCost();
    }
}
