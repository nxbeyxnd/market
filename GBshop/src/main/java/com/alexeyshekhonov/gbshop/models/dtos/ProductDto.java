package com.alexeyshekhonov.gbshop.models.dtos;

import com.alexeyshekhonov.gbshop.models.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private int cost;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }
}
