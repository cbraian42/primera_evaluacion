package com.mobydigital.gestor_turnos.mapper;

import com.mobydigital.gestor_turnos.dto.PacienteRequestDTO;
import com.mobydigital.gestor_turnos.dto.PacienteResponseDTO;
import com.mobydigital.gestor_turnos.model.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    public Paciente toEntity(PacienteRequestDTO dto) {
        return Paciente.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .dni(dto.getDni())
                .email(dto.getEmail())
                .build();
    }

    public PacienteResponseDTO toResponseDTO(Paciente entidad) {
        return PacienteResponseDTO.builder()
                .id(entidad.getId())
                .nombre(entidad.getNombre())
                .apellido(entidad.getApellido())
                .dni(entidad.getDni())
                .email(entidad.getEmail())
                .build();
    }
}