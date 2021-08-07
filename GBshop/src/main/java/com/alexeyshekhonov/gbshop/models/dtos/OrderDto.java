package com.alexeyshekhonov.gbshop.models.dtos;

import com.alexeyshekhonov.gbshop.models.entities.Order;
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
        this.productTitle = o.getProduct().getTitle();
        this.count = o.getCount();
        this.costPerProduct = o.getCostPerItem();
        this.cost = o.getCost();
    }
}
