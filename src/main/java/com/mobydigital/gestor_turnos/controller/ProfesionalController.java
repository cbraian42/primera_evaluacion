package com.mobydigital.gestor_turnos.controller;

import com.mobydigital.gestor_turnos.dto.ProfesionalRequestDTO;
import com.mobydigital.gestor_turnos.dto.ProfesionalResponseDTO;
import com.mobydigital.gestor_turnos.service.IProfesionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesionales")
@RequiredArgsConstructor
public class ProfesionalController {

    private final IProfesionalService profesionalService;

    @PostMapping
    public ResponseEntity<ProfesionalResponseDTO> crearProfesional(@Valid @RequestBody ProfesionalRequestDTO requestDTO) {
        ProfesionalResponseDTO response = profesionalService.crearProfesional(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesionalResponseDTO> obtenerProfesional(@PathVariable Long id) {
        ProfesionalResponseDTO response = profesionalService.obtenerProfesionalPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfesionalResponseDTO>> listarProfesionales(
            @RequestParam(required = false) String especialidad) {

        List<ProfesionalResponseDTO> response = profesionalService.listarProfesionales(especialidad);
        return ResponseEntity.ok(response);
    }
}