package com.mobydigital.gestor_turnos.mapper;

import com.mobydigital.gestor_turnos.dto.TurnoRequestDTO;
import com.mobydigital.gestor_turnos.dto.TurnoResponseDTO;
import com.mobydigital.gestor_turnos.model.Paciente;
import com.mobydigital.gestor_turnos.model.Profesional;
import com.mobydigital.gestor_turnos.model.Turno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TurnoMapper {

    private final PacienteMapper pacienteMapper;
    private final ProfesionalMapper profesionalMapper;

    public Turno toEntity(TurnoRequestDTO dto, Paciente paciente, Profesional profesional) {
        return Turno.builder()
                .paciente(paciente)
                .profesional(profesional)
                .fecha(dto.getFecha())
                .build();
    }

    public TurnoResponseDTO toResponseDTO(Turno entidad) {
        return TurnoResponseDTO.builder()
                .id(entidad.getId())
                .paciente(pacienteMapper.toResponseDTO(entidad.getPaciente()))
                .profesional(profesionalMapper.toResponseDTO(entidad.getProfesional()))
                .fecha(entidad.getFecha())
                .build();
    }
}