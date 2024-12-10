package com.xolit.e_commerce.ecommerce.controller;

import com.xolit.e_commerce.ecommerce.dto.OrderRequestDTO;
import com.xolit.e_commerce.ecommerce.dto.OrderResponseDTO;
import com.xolit.e_commerce.ecommerce.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder() {
        OrderRequestDTO request = new OrderRequestDTO();
        OrderResponseDTO response = new OrderResponseDTO();

        when(orderService.createOrder(request)).thenReturn(response);

        ResponseEntity<OrderResponseDTO> result = orderController.createOrder(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(orderService, times(1)).createOrder(request);
    }

    @Test
    void pruebaGET() {
        ResponseEntity<String> result = orderController.pruebaGET();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Hola Mundo", result.getBody());
    }
}
