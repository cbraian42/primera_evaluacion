package com.mobydigital.gestor_turnos.service;

import com.mobydigital.gestor_turnos.dto.TurnoRequestDTO;
import com.mobydigital.gestor_turnos.dto.TurnoResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {

    TurnoResponseDTO crearTurno(TurnoRequestDTO requestDTO);

    List<TurnoResponseDTO> listarTodos();

    List<TurnoResponseDTO> listarPorFecha(LocalDate fecha);

    List<TurnoResponseDTO> listarPorRangoFechas(LocalDate desde, LocalDate hasta);

    void eliminarTurno(Long id);
}