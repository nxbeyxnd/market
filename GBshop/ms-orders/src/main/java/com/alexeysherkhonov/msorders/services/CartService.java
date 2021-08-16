package com.alexeysherkhonov.msorders.services;

import com.alexeysherkhonov.core.exceptions.ResourceNotFoundException;
import com.alexeysherkhonov.msorders.entities.CartDto;
import com.alexeysherkhonov.msorders.entities.Order;
import com.alexeysherkhonov.msorders.entities.OrderDto;
import com.alexeysherkhonov.msorders.repositories.OrderRepository;
import com.alexeysherkhonov.products.entities.Product;
import com.alexeysherkhonov.products.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductsRepository productsRepository;

    public CartDto findAll() {
        List<OrderDto> o = orderRepository.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
        return new CartDto(o, calculateCost(o), calculateCount(o));
    }

    public void addToCartById(Long id) {
        for (Order o : orderRepository.findAll()) {
            if (o.getProduct().getId().equals(id)) {
                o.setCost(calculateCost(o));
                o.setCount(calculateCount(o));
                orderRepository.save(o);
                return;
            }
        }
        Product p = productsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));
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
