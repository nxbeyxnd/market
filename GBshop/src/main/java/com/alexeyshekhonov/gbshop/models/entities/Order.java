package com.alexeyshekhonov.gbshop.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "order_products")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    String title;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    int count;

    @Column(name = "cost_per_item")
    int costPerItem;

    @Column(name = "cost")
    int cost;

    public Order(Product product) {
        this.product = product;
        this.count = 1;
        this.costPerItem = product.getCost();
        this.cost = this.costPerItem;
    }
}
