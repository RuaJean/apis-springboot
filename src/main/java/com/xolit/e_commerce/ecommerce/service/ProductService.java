package com.xolit.e_commerce.ecommerce.service;

import com.xolit.e_commerce.ecommerce.entity.Product;
import com.xolit.e_commerce.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
    // Métodos adicionales para manejar stock, etc.
}
