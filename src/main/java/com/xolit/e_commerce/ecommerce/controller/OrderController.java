package com.xolit.e_commerce.ecommerce.controller;

import com.xolit.e_commerce.ecommerce.dto.OrderRequestDTO;
import com.xolit.e_commerce.ecommerce.dto.OrderResponseDTO;
import com.xolit.e_commerce.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/ecommerce/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Crear una orden", description = "Crea una nueva orden en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orden creada exitosamente",
                    content = @Content(schema = @Schema(implementation = OrderResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO request) {
        OrderResponseDTO response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Prueba de endpoint GET", description = "Devuelve un saludo para probar el endpoint GET.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saludo obtenido exitosamente")
    })
    @GetMapping("/")
    public ResponseEntity<String> pruebaGET() {
        return ResponseEntity.ok("Hola Mundo");
    }
}
