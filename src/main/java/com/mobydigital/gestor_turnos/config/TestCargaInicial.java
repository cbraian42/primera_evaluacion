package com.mobydigital.gestor_turnos.config;

import com.mobydigital.gestor_turnos.model.Paciente;
import com.mobydigital.gestor_turnos.model.Profesional;
import com.mobydigital.gestor_turnos.model.Turno;
import com.mobydigital.gestor_turnos.repository.PacienteRepository;
import com.mobydigital.gestor_turnos.repository.ProfesionalRepository;
import com.mobydigital.gestor_turnos.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestCargaInicial implements CommandLineRunner {

    private final PacienteRepository pacienteRepository;
    private final ProfesionalRepository profesionalRepository;
    private final TurnoRepository turnoRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Iniciando carga de datos de prueba...");

        Paciente paciente1 = Paciente.builder()
                .nombre("Braian")
                .apellido("Castro")
                .dni("12345678")
                .email("braian@mobydigital.com")
                .build();

        Paciente paciente2 = Paciente.builder()
                .nombre("Luna")
                .apellido("Gomez")
                .dni("87654321")
                .email("luna@mobydigital.com")
                .build();

        pacienteRepository.save(paciente1);
        pacienteRepository.save(paciente2);

        Profesional profesional1 = Profesional.builder()
                .nombreCompleto("Dr. Gregory House")
                .especialidad("clinica")
                .build();

        Profesional profesional2 = Profesional.builder()
                .nombreCompleto("Dra. Meredith Grey")
                .especialidad("cirugia")
                .build();

        Profesional profesional3 = Profesional.builder()
                .nombreCompleto("Dr. Steve Martin")
                .especialidad("odontologia")
                .build();

        profesionalRepository.save(profesional1);
        profesionalRepository.save(profesional2);
        profesionalRepository.save(profesional3);

        Turno turno1 = Turno.builder()
                .paciente(paciente1)
                .profesional(profesional1)
                .fecha(LocalDate.now().plusDays(5)) // Turno para dentro de 5 días
                .build();

        Turno turno2 = Turno.builder()
                .paciente(paciente2)
                .profesional(profesional3)
                .fecha(LocalDate.now().plusDays(10)) // Turno para dentro de 10 días
                .build();

        turnoRepository.save(turno1);
        turnoRepository.save(turno2);

        System.out.println("¡Carga de datos finalizada con éxito!");
    }
}