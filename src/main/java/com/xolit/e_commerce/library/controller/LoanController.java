package com.xolit.e_commerce.library.controller;

import com.xolit.e_commerce.library.dto.LoanRequestDTO;
import com.xolit.e_commerce.library.dto.LoanResponseDTO;
import com.xolit.e_commerce.library.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/library/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(summary = "Registra el préstamo", description = "Registra el préstamo de un libro a un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Préstamo registrado exitosamente",
                    content = @Content(schema = @Schema(implementation = LoanResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o libro no disponible", content = @Content)
    })
    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody LoanRequestDTO request) {
        LoanResponseDTO response = loanService.createLoan(request);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Devolver un libro", description = "Registra la devolución de un libro y lo marca como disponible.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devolución registrada exitosamente",
                    content = @Content(schema = @Schema(implementation = LoanResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado", content = @Content)
    })
    @PutMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnBook(@PathVariable Integer id) {
        LoanResponseDTO response = loanService.returnBook(id);
        return ResponseEntity.ok(response);
    }
}
