package com.alexeyshekhonov.gbshop.services;

import com.alexeyshekhonov.gbshop.models.dtos.ProductDto;
import com.alexeyshekhonov.gbshop.models.entities.Product;
import com.alexeyshekhonov.gbshop.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductsRepository productsRepository;

    public Optional<ProductDto> findById(Long id){
        return productsRepository.findById(id).map(ProductDto::new);
    }

    public Product saveOrUpdate(Product product){
        return productsRepository.save(product);
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        if(page < 0)
            throw new RuntimeException();
        return productsRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }

    public void deleteProductById(Long id){
        productsRepository.deleteById(id);
    }

    public void deleteAll(){
        productsRepository.deleteAll();
    }
}
