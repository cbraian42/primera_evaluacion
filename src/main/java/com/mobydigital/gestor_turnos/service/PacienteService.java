package com.mobydigital.gestor_turnos.service;

import com.mobydigital.gestor_turnos.dto.PacienteRequestDTO;
import com.mobydigital.gestor_turnos.dto.PacienteResponseDTO;
import com.mobydigital.gestor_turnos.exception.DatoInvalidoException;
import com.mobydigital.gestor_turnos.exception.RecursoNoEncontradoException;
import com.mobydigital.gestor_turnos.mapper.PacienteMapper;
import com.mobydigital.gestor_turnos.model.Paciente;
import com.mobydigital.gestor_turnos.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService implements IPacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    @Override
    public PacienteResponseDTO crearPaciente(PacienteRequestDTO requestDTO) {
        if (pacienteRepository.findByDni(requestDTO.getDni()).isPresent()) {
            throw new DatoInvalidoException("Ya existe un paciente registrado con el DNI: " + requestDTO.getDni());
        }

        if (pacienteRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new DatoInvalidoException("Ya existe un paciente registrado con el email: " + requestDTO.getEmail());
        }

        Paciente paciente = pacienteMapper.toEntity(requestDTO);
        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        return pacienteMapper.toResponseDTO(pacienteGuardado);
    }

    @Override
    public PacienteResponseDTO obtenerPacientePorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el paciente con ID: " + id));

        return pacienteMapper.toResponseDTO(paciente);
    }

    @Override
    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se puede eliminar. Paciente no encontrado con ID: " + id));

        pacienteRepository.delete(paciente);
    }
}
