package com.xolit.e_commerce.reservation.controller;

import com.xolit.e_commerce.reservation.dto.CustomerRequestDTO;
import com.xolit.e_commerce.reservation.dto.CustomerResponseDTO;
import com.xolit.e_commerce.reservation.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateCustomer() {
        CustomerRequestDTO request = new CustomerRequestDTO();
        CustomerResponseDTO response = new CustomerResponseDTO();

        when(customerService.createCustomer(request)).thenReturn(response);

        ResponseEntity<CustomerResponseDTO> result = customerController.createCustomer(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(customerService, times(1)).createCustomer(request);
    }

    @Test
    void testListCustomers() {
        List<CustomerResponseDTO> customers = List.of(new CustomerResponseDTO());

        when(customerService.listAll()).thenReturn(customers);

        ResponseEntity<List<CustomerResponseDTO>> result = customerController.listCustomers();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
        verify(customerService, times(1)).listAll();
    }
}
