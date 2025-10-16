package com.atencion.medica.servicios;

import com.atencion.medica.dtos.HistorialMedicoDTO;
import com.atencion.medica.dtos.MedicoDTO;
import com.atencion.medica.dtos.PacienteDTO;
import com.atencion.medica.dtos.RecetaDTO;

import java.util.List;

public interface HospitalService {
    //Todo lo que le pertenece al medico
    MedicoDTO crearMedico(MedicoDTO medicoDTO);
    List<MedicoDTO> obtenerTodosLosMedicos();
    MedicoDTO obtenerMedicoPorId(Long id);

    //Todo lo que le pertenece al paciente
    HistorialMedicoDTO obtenerHistorialMedico(String historialId, int page, int size);
    PacienteDTO crearPaciente(PacienteDTO pacienteDTO);

    //Todo lo que le pertenece a la reseta
    RecetaDTO crearReceta(RecetaDTO recetaDTO);
}
