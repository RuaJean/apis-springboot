package com.xolit.e_commerce.reservation.controller;

import com.xolit.e_commerce.reservation.dto.ReservationRequestDTO;
import com.xolit.e_commerce.reservation.dto.ReservationResponseDTO;
import com.xolit.e_commerce.reservation.service.ReservationService;
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

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation() {
        ReservationRequestDTO request = new ReservationRequestDTO();
        ReservationResponseDTO response = new ReservationResponseDTO();

        when(reservationService.createReservation(request)).thenReturn(response);

        ResponseEntity<ReservationResponseDTO> result = reservationController.createReservation(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(reservationService, times(1)).createReservation(request);
    }
}
