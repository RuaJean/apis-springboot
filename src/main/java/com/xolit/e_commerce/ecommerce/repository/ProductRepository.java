package com.xolit.e_commerce.ecommerce.repository;

import com.xolit.e_commerce.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
