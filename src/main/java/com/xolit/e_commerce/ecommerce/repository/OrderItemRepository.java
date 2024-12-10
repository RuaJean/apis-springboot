package com.xolit.e_commerce.ecommerce.repository;

import com.xolit.e_commerce.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
