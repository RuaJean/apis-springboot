package com.xolit.e_commerce.ecommerce.repository;

import com.xolit.e_commerce.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
