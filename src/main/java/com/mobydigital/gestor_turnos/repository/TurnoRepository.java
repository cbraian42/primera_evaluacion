package com.mobydigital.gestor_turnos.repository;

import com.mobydigital.gestor_turnos.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

    List<Turno> findByFecha(LocalDate fecha);

    List<Turno> findByFechaBetween(LocalDate desde, LocalDate hasta);

    Optional<Turno> findByPacienteIdAndProfesionalIdAndFecha(Long pacienteId, Long profesionalId, LocalDate fecha);
}