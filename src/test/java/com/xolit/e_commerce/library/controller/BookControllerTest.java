package com.xolit.e_commerce.library.controller;

import com.xolit.e_commerce.library.dto.BookRequestDTO;
import com.xolit.e_commerce.library.dto.BookResponseDTO;
import com.xolit.e_commerce.library.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBook() {
        BookRequestDTO request = new BookRequestDTO();
        BookResponseDTO response = new BookResponseDTO();

        when(bookService.createBook(request)).thenReturn(response);

        ResponseEntity<BookResponseDTO> result = bookController.createBook(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(bookService, times(1)).createBook(request);
    }

    @Test
    void testListAvailableBooks() {
        List<BookResponseDTO> books = List.of(new BookResponseDTO());

        when(bookService.listAvailableBooks(null, null)).thenReturn(books);

        ResponseEntity<List<BookResponseDTO>> result = bookController.listAvailableBooks(null, null);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, Objects.requireNonNull(result.getBody()).size());
        verify(bookService, times(1)).listAvailableBooks(null, null);
    }
}
