package com.mobydigital.gestor_turnos.service;

import com.mobydigital.gestor_turnos.dto.ProfesionalRequestDTO;
import com.mobydigital.gestor_turnos.dto.ProfesionalResponseDTO;

import java.util.List;

public interface IProfesionalService {

    ProfesionalResponseDTO crearProfesional(ProfesionalRequestDTO requestDTO);

    ProfesionalResponseDTO obtenerProfesionalPorId(Long id);

    List<ProfesionalResponseDTO> listarProfesionales(String especialidad);
}
