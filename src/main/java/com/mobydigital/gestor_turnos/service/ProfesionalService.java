package com.mobydigital.gestor_turnos.service;

import com.mobydigital.gestor_turnos.dto.ProfesionalRequestDTO;
import com.mobydigital.gestor_turnos.dto.ProfesionalResponseDTO;
import com.mobydigital.gestor_turnos.exception.RecursoNoEncontradoException;
import com.mobydigital.gestor_turnos.mapper.ProfesionalMapper;
import com.mobydigital.gestor_turnos.model.Profesional;
import com.mobydigital.gestor_turnos.repository.ProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfesionalService implements IProfesionalService {

    private final ProfesionalRepository profesionalRepository;
    private final ProfesionalMapper profesionalMapper;

    @Override
    public ProfesionalResponseDTO crearProfesional(ProfesionalRequestDTO requestDTO) {
        Profesional profesional = profesionalMapper.toEntity(requestDTO);

        Profesional profesionalGuardado = profesionalRepository.save(profesional);

        return profesionalMapper.toResponseDTO(profesionalGuardado);
    }

    @Override
    public ProfesionalResponseDTO obtenerProfesionalPorId(Long id) {
        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el profesional con ID: " + id));

        return profesionalMapper.toResponseDTO(profesional);
    }

    @Override
    public List<ProfesionalResponseDTO> listarProfesionales(String especialidad) {
        List<Profesional> profesionales;

        if (especialidad != null && !especialidad.trim().isEmpty()) {
            profesionales = profesionalRepository.findByEspecialidad(especialidad);
        } else {
            profesionales = profesionalRepository.findAll();
        }

        return profesionales.stream()
                .map(profesionalMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}