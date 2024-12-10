package com.xolit.e_commerce.library.controller;

import com.xolit.e_commerce.library.dto.LoanRequestDTO;
import com.xolit.e_commerce.library.dto.LoanResponseDTO;
import com.xolit.e_commerce.library.service.LoanService;
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

class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateLoan() {
        LoanRequestDTO request = new LoanRequestDTO();
        LoanResponseDTO response = new LoanResponseDTO();

        when(loanService.createLoan(request)).thenReturn(response);

        ResponseEntity<LoanResponseDTO> result = loanController.createLoan(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(loanService, times(1)).createLoan(request);
    }

    @Test
    void testReturnBook() {
        int loanId = 1;
        LoanResponseDTO response = new LoanResponseDTO();

        when(loanService.returnBook(loanId)).thenReturn(response);

        ResponseEntity<LoanResponseDTO> result = loanController.returnBook(loanId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(loanService, times(1)).returnBook(loanId);
    }
}
