package com.xolit.e_commerce.reservation.controller;

import com.xolit.e_commerce.reservation.dto.RestaurantTableRequestDTO;
import com.xolit.e_commerce.reservation.dto.RestaurantTableResponseDTO;
import com.xolit.e_commerce.reservation.service.RestaurantTableService;
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

class RestaurantTableControllerTest {

    @Mock
    private RestaurantTableService tableService;

    @InjectMocks
    private RestaurantTableController restaurantTableController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTable() {
        RestaurantTableRequestDTO request = new RestaurantTableRequestDTO();
        RestaurantTableResponseDTO response = new RestaurantTableResponseDTO();

        when(tableService.createTable(request)).thenReturn(response);

        ResponseEntity<RestaurantTableResponseDTO> result = restaurantTableController.createTable(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(tableService, times(1)).createTable(request);
    }

    @Test
    void testListTables() {
        List<RestaurantTableResponseDTO> tables = List.of(new RestaurantTableResponseDTO());

        when(tableService.listAll()).thenReturn(tables);

        ResponseEntity<List<RestaurantTableResponseDTO>> result = restaurantTableController.listTables();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
        verify(tableService, times(1)).listAll();
    }
}
