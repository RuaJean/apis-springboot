package com.xolit.e_commerce.reservation.repository;

import com.xolit.e_commerce.reservation.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Integer> {
}
