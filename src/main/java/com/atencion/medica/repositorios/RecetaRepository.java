package com.atencion.medica.repositorios;

import com.atencion.medica.entidades.Receta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    // Buscar recetas por el ID del historial clínico del paciente
    Page<Receta> findByPacienteHistorialClinicoId(String historialId, Pageable pageable);

    // Buscar todas las recetas de un paciente
    List<Receta> findByPacienteId(Long pacienteId);
}