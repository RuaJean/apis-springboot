package com.xolit.e_commerce.reservation.service;

import com.xolit.e_commerce.reservation.dto.CustomerRequestDTO;
import com.xolit.e_commerce.reservation.dto.CustomerResponseDTO;
import com.xolit.e_commerce.reservation.entity.Customer;
import com.xolit.e_commerce.reservation.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO request) {
        Customer c = new Customer();
        c.setName(request.getName());
        c.setEmail(request.getEmail());
        c.setPhone(request.getPhone());
        c = customerRepository.save(c);
        return toResponseDTO(c);
    }

    public Customer getById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El cliente no existe"));
    }

    public List<CustomerResponseDTO> listAll() {
        return customerRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    private CustomerResponseDTO toResponseDTO(Customer c) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setEmail(c.getEmail());
        dto.setPhone(c.getPhone());
        return dto;
    }
}
