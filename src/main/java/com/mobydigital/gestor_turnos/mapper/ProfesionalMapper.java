package com.mobydigital.gestor_turnos.mapper;

import com.mobydigital.gestor_turnos.dto.ProfesionalRequestDTO;
import com.mobydigital.gestor_turnos.dto.ProfesionalResponseDTO;
import com.mobydigital.gestor_turnos.model.Profesional;
import org.springframework.stereotype.Component;

@Component
public class ProfesionalMapper {

    public Profesional toEntity(ProfesionalRequestDTO dto) {
        return Profesional.builder()
                .nombreCompleto(dto.getNombreCompleto())
                .especialidad(dto.getEspecialidad())
                .build();
    }

    public ProfesionalResponseDTO toResponseDTO(Profesional entidad) {
        return ProfesionalResponseDTO.builder()
                .id(entidad.getId())
                .nombreCompleto(entidad.getNombreCompleto())
                .especialidad(entidad.getEspecialidad())
                .build();
    }
}