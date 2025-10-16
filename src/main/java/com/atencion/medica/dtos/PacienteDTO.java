package com.atencion.medica.dtos;

import lombok.Data;

import java.util.Date;
@Data
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String telefono;
    private String historialClinicoId;
}
