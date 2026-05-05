package com.mobydigital.gestor_turnos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfesionalResponseDTO {

    private Long id;
    private String nombreCompleto;
    private String especialidad;
}