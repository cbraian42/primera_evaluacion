package com.mobydigital.gestor_turnos.controller;

import com.mobydigital.gestor_turnos.dto.TurnoRequestDTO;
import com.mobydigital.gestor_turnos.dto.TurnoResponseDTO;
import com.mobydigital.gestor_turnos.service.ITurnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final ITurnoService turnoService;

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> registrarTurno(@Valid @RequestBody TurnoRequestDTO requestDTO) {
        TurnoResponseDTO response = turnoService.crearTurno(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> listarTurnos(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {

        List<TurnoResponseDTO> response;
        if (desde != null && hasta != null) {
            response = turnoService.listarPorRangoFechas(desde, hasta);
        } else {
            response = turnoService.listarTodos();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<TurnoResponseDTO>> listarPorFechaExacta(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        List<TurnoResponseDTO> response = turnoService.listarPorFecha(fecha);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }
}