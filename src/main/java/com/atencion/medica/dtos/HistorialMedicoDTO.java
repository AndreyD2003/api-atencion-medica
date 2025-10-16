package com.atencion.medica.dtos;

import java.util.List;

public class HistorialMedicoDTO {
    private String historialClinicoId;
    private String nombrePaciente;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<RecetaDTO> recetasDTO; // Lista de recetas del paciente
}
