package com.alexeysherkhonov.msorders.controllers;

import com.alexeysherkhonov.msorders.services.CartService;
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


    @PostMapping({"/{id}"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addToCart(@PathVariable Long id) {
        cartService.addToCartById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        cartService.deleteAll();
    }
}
