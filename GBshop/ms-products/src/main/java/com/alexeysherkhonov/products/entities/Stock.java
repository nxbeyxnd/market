package com.alexeysherkhonov.products.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "id")
    private int productId;

    @Column(name = "stock_count")
    private int stockCount;
}
