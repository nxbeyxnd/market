package com.alexeysherkhonov.msorders.entities;

import com.alexeysherkhonov.products.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "title")
    String title;

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
