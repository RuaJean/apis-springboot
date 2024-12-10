package com.xolit.e_commerce.reservation.repository;

import com.xolit.e_commerce.reservation.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
