package com.alexeyshekhonov.gbshop.models.entities;

import lombok.Data;

import javax.persistence.*;

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
