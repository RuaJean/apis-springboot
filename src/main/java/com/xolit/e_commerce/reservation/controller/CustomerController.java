package com.xolit.e_commerce.reservation.controller;

import com.xolit.e_commerce.reservation.dto.CustomerRequestDTO;
import com.xolit.e_commerce.reservation.dto.CustomerResponseDTO;
import com.xolit.e_commerce.reservation.service.CustomerService;
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
@RequestMapping("/api/reservation/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Crear un cliente", description = "Registra un cliente del restaurante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente registrado exitosamente",
                    content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO request) {
        CustomerResponseDTO response = customerService.createCustomer(request);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Listar clientes", description = "Obtiene la lista de clientes registrados en el restaurante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> listCustomers() {
        return ResponseEntity.ok(customerService.listAll());
    }
}
