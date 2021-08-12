package com.alexeysherkhonov.products.controllers;

import com.alexeysherkhonov.core.exceptions.ResourceNotFoundException;
import com.alexeysherkhonov.products.entities.Product;
import com.alexeysherkhonov.products.entities.ProductDto;
import com.alexeysherkhonov.products.repositories.specifications.ProductSpecifications;
import com.alexeysherkhonov.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "s", defaultValue = "10") Integer size
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(ProductSpecifications.build(params), page, size);

    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.findbyIdDto(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }
}
