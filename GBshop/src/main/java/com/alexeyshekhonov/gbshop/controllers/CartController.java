package com.alexeyshekhonov.gbshop.controllers;

import com.alexeyshekhonov.gbshop.models.dtos.CartDto;
import com.alexeyshekhonov.gbshop.models.dtos.ProductIdDto;
import com.alexeyshekhonov.gbshop.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public CartDto findAll() {
        return cartService.findAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToCart(@RequestBody ProductIdDto productIdDto) {
        cartService.addToCartById(productIdDto);
    }

    @DeleteMapping
    public void deleteAll() {
        cartService.deleteAll();
    }
}
