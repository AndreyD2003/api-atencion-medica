package com.atencion.medica.dtos;

import com.atencion.medica.enums.EstadoReceta;

import java.util.Date;

public class RecetaDTO {
    private Long id;
    private String medicamento;
    private String dosis;
    private Date fechaEmision;
    private EstadoReceta estadoReceta;
    private MedicoDTO medicoDTO;      // Incluye el DTO del médico que la emitió
    private PacienteDTO pacienteDTO;  // Incluye el DTO del paciente
}
