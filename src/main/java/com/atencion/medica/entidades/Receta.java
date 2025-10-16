package com.atencion.medica.entidades;

import com.atencion.medica.enums.EstadoReceta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicamento;
    private String dosis;
    private Date fechaEmision;

    @Enumerated(EnumType.STRING)
    private EstadoReceta estadoReceta;

    // Relación con Médico (Muchos a Uno)
    @ManyToOne
    private Medico medico;

    // Relación con Paciente (Muchos a Uno)
    @ManyToOne
    private Paciente paciente;
}
