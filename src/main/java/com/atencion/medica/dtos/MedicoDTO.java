package com.atencion.medica.dtos;

import com.atencion.medica.enums.EspecialidadMedico;
import lombok.Data;

@Data
public class MedicoDTO {
    private Long id;
    private String nombreCompleto;
    private String cedulaProfesional;
    private EspecialidadMedico especialidad;
}
