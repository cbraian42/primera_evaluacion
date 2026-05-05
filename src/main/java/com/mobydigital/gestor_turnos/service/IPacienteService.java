package com.mobydigital.gestor_turnos.service;

import com.mobydigital.gestor_turnos.dto.PacienteRequestDTO;
import com.mobydigital.gestor_turnos.dto.PacienteResponseDTO;

import java.util.List;

public interface IPacienteService {

    PacienteResponseDTO crearPaciente(PacienteRequestDTO requestDTO);

    PacienteResponseDTO obtenerPacientePorId(Long id);

    List<PacienteResponseDTO> listarTodos();

    void eliminarPaciente(Long id);
}