package com.mobydigital.gestor_turnos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurnoResponseDTO {

    private Long id;
    private PacienteResponseDTO paciente;
    private ProfesionalResponseDTO profesional;
    private LocalDate fecha;
}
