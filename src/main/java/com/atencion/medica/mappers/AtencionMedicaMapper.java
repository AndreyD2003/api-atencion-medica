package com.atencion.medica.mappers;

import com.atencion.medica.dtos.MedicoDTO;
import com.atencion.medica.dtos.PacienteDTO;
import com.atencion.medica.dtos.RecetaDTO;
import com.atencion.medica.entidades.Medico;
import com.atencion.medica.entidades.Paciente;
import com.atencion.medica.entidades.Receta;

import java.util.List;

public interface AtencionMedicaMapper {
    MedicoDTO medicoToMedicoDTO(Medico medico);
    Medico medicoDTOToMedico(MedicoDTO medicoDTO);

    PacienteDTO pacienteToPacienteDTO(Paciente paciente);
    Paciente pacienteDTOToPaciente(PacienteDTO pacienteDTO);

    RecetaDTO recetaToRecetaDTO(Receta receta);
    Receta recetaDTOToReceta(RecetaDTO recetaDTO);

    List<RecetaDTO> recetasToRecetasDTO(List<Receta> recetas);
}
