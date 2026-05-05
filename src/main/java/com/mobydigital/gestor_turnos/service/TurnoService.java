package com.mobydigital.gestor_turnos.service;

import com.mobydigital.gestor_turnos.dto.TurnoRequestDTO;
import com.mobydigital.gestor_turnos.dto.TurnoResponseDTO;
import com.mobydigital.gestor_turnos.exception.DatoInvalidoException;
import com.mobydigital.gestor_turnos.exception.RecursoNoEncontradoException;
import com.mobydigital.gestor_turnos.mapper.TurnoMapper;
import com.mobydigital.gestor_turnos.model.Paciente;
import com.mobydigital.gestor_turnos.model.Profesional;
import com.mobydigital.gestor_turnos.model.Turno;
import com.mobydigital.gestor_turnos.repository.PacienteRepository;
import com.mobydigital.gestor_turnos.repository.ProfesionalRepository;
import com.mobydigital.gestor_turnos.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurnoService implements ITurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfesionalRepository profesionalRepository;
    private final TurnoMapper turnoMapper;

    @Override
    public TurnoResponseDTO crearTurno(TurnoRequestDTO requestDTO) {

        Paciente paciente = pacienteRepository.findById(requestDTO.getPacienteId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se puede registrar el turno. Paciente no encontrado con ID: " + requestDTO.getPacienteId()));

        Profesional profesional = profesionalRepository.findById(requestDTO.getProfesionalId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se puede registrar el turno. Profesional no encontrado con ID: " + requestDTO.getProfesionalId()));

        if (turnoRepository.findByPacienteIdAndProfesionalIdAndFecha(
                requestDTO.getPacienteId(), requestDTO.getProfesionalId(), requestDTO.getFecha()).isPresent()) {
            throw new DatoInvalidoException("El paciente ya tiene un turno registrado con este profesional en la fecha indicada.");
        }

        Turno turno = turnoMapper.toEntity(requestDTO, paciente, profesional);
        Turno turnoGuardado = turnoRepository.save(turno);

        return turnoMapper.toResponseDTO(turnoGuardado);
    }

    @Override
    public List<TurnoResponseDTO> listarTodos() {
        return turnoRepository.findAll().stream()
                .map(turnoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TurnoResponseDTO> listarPorFecha(LocalDate fecha) {
        return turnoRepository.findByFecha(fecha).stream()
                .map(turnoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TurnoResponseDTO> listarPorRangoFechas(LocalDate desde, LocalDate hasta) {
        return turnoRepository.findByFechaBetween(desde, hasta).stream()
                .map(turnoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarTurno(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el turno con ID: " + id));
        turnoRepository.delete(turno);
    }
}