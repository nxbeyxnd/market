package com.alexeyshekhonov.gbshop.models.dtos;

import com.alexeyshekhonov.gbshop.models.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductIdDto {
    private Long id;

    public ProductIdDto(Product product) {
        this.id = product.getId();
    }
}
