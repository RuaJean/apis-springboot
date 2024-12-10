package com.xolit.e_commerce.library.controller;

import com.xolit.e_commerce.library.dto.UserRequestDTO;
import com.xolit.e_commerce.library.dto.UserResponseDTO;
import com.xolit.e_commerce.library.service.LibraryUserService;
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

class LibraryUserControllerTest {

    @Mock
    private LibraryUserService userService;

    @InjectMocks
    private LibraryUserController libraryUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        UserRequestDTO request = new UserRequestDTO();
        UserResponseDTO response = new UserResponseDTO();

        when(userService.createUser(request)).thenReturn(response);

        ResponseEntity<UserResponseDTO> result = libraryUserController.createUser(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(userService, times(1)).createUser(request);
    }

    @Test
    void testListUsers() {
        List<UserResponseDTO> users = List.of(new UserResponseDTO());

        when(userService.listUsers()).thenReturn(users);

        ResponseEntity<List<UserResponseDTO>> result = libraryUserController.listUsers();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
        verify(userService, times(1)).listUsers();
    }
}
