package com.xolit.e_commerce.library.controller;

import com.xolit.e_commerce.library.dto.UserRequestDTO;
import com.xolit.e_commerce.library.dto.UserResponseDTO;
import com.xolit.e_commerce.library.service.LibraryUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/library/users")
public class LibraryUserController {

    private final LibraryUserService userService;

    public LibraryUserController(LibraryUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Crear un usuario", description = "Registra un nuevo usuario de la biblioteca.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request) {
        UserResponseDTO user = userService.createUser(request);
        return ResponseEntity.status(201).body(user);
    }

    @Operation(summary = "Listar usuarios", description = "Obtiene la lista de usuarios registrados en la biblioteca.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }
}
