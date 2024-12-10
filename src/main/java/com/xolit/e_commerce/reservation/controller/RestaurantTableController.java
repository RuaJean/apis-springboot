package com.xolit.e_commerce.reservation.controller;

import com.xolit.e_commerce.reservation.dto.RestaurantTableRequestDTO;
import com.xolit.e_commerce.reservation.dto.RestaurantTableResponseDTO;
import com.xolit.e_commerce.reservation.service.RestaurantTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reservation/tables")
public class RestaurantTableController {

    private final RestaurantTableService tableService;

    public RestaurantTableController(RestaurantTableService tableService) {
        this.tableService = tableService;
    }

    @Operation(summary = "Crear una mesa", description = "Registra una nueva mesa en el restaurante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mesa creada exitosamente",
                    content = @Content(schema = @Schema(implementation = RestaurantTableResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<RestaurantTableResponseDTO> createTable(@Valid @RequestBody RestaurantTableRequestDTO request) {
        RestaurantTableResponseDTO response = tableService.createTable(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Listar mesas", description = "Obtiene la lista de mesas registradas en el restaurante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de mesas obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = RestaurantTableResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<RestaurantTableResponseDTO>> listTables() {
        return ResponseEntity.ok(tableService.listAll());
    }
}
