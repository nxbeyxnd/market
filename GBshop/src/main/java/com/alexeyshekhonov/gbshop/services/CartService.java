package com.alexeyshekhonov.gbshop.services;

import com.alexeyshekhonov.gbshop.exceptions.ResourceNotFoundException;
import com.alexeyshekhonov.gbshop.models.dtos.CartDto;
import com.alexeyshekhonov.gbshop.models.dtos.OrderDto;
import com.alexeyshekhonov.gbshop.models.dtos.ProductIdDto;
import com.alexeyshekhonov.gbshop.models.entities.Order;
import com.alexeyshekhonov.gbshop.models.entities.Product;
import com.alexeyshekhonov.gbshop.repositories.OrderRepository;
import com.alexeyshekhonov.gbshop.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductsRepository productsRepository;

    public CartDto findAll() {
        List<OrderDto> o = orderRepository.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
        return new CartDto(o, calculateCost(o), calculateCount(o));
    }

    public void addToCartById(ProductIdDto productIdDto) {
        for (Order o : orderRepository.findAll()) {
            if (o.getProduct().getId().equals(productIdDto.getId())) {
                o.setCost(calculateCost(o));
                o.setCount(calculateCount(o));
                orderRepository.save(o);
                return;
            }
        }
        Product p = productsRepository.findById(productIdDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productIdDto.getId() + " not found"));
        Order order = new Order(p);
        order.setTitle(p.getTitle());
        orderRepository.save(order);
        order.setCost(calculateCost(order));
        order.setCount(calculateCount(order));
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    private int calculateCost(Order o) {
        return o.getCost() + o.getCostPerItem();
    }

    private int calculateCount(Order o) {
        int totalCount = o.getCount();
        return ++totalCount;
    }

    private int calculateCost(List<OrderDto> o) {
        int totalCost = 0;
        for (int i = 0; i < o.size(); i++) {
            totalCost += o.get(i).getCost();
        }
        return totalCost;
    }

    private int calculateCount(List<OrderDto> o) {
        int totalCount = 0;
        for (int i = 0; i < o.size(); i++) {
            totalCount += o.get(i).getCount();
        }
        return totalCount;
    }
}
