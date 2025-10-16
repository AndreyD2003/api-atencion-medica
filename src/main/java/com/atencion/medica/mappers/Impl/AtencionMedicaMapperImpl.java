package com.atencion.medica.mappers.Impl;

import com.atencion.medica.dtos.MedicoDTO;
import com.atencion.medica.dtos.PacienteDTO;
import com.atencion.medica.dtos.RecetaDTO;
import com.atencion.medica.entidades.Medico;
import com.atencion.medica.entidades.Paciente;
import com.atencion.medica.entidades.Receta;
import com.atencion.medica.mappers.AtencionMedicaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtencionMedicaMapperImpl implements AtencionMedicaMapper {

    @Override
    public MedicoDTO medicoToMedicoDTO(Medico medico) {
        if (medico == null) return null;
        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getId());
        dto.setNombreCompleto(medico.getNombre() + " " + medico.getApellido());
        dto.setCedulaProfesional(medico.getCedulaProfesional());
        dto.setEspecialidad(medico.getEspecialidad());
        return dto;
    }

    @Override
    public Medico medicoDTOToMedico(MedicoDTO medicoDTO) {
        if (medicoDTO == null) return null;
        Medico medico = new Medico();
        medico.setId(medicoDTO.getId());
        // Se asume que el nombre completo se divide en nombre y apellido en la lógica de servicio si es necesario
        medico.setCedulaProfesional(medicoDTO.getCedulaProfesional());
        medico.setEspecialidad(medicoDTO.getEspecialidad());
        return medico;
    }

    @Override
    public PacienteDTO pacienteToPacienteDTO(Paciente paciente) {
        if (paciente == null) return null;
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setTelefono(paciente.getTelefono());
        dto.setHistorialClinicoId(paciente.getHistorialClinicoId());
        return dto;
    }

    @Override
    public Paciente pacienteDTOToPaciente(PacienteDTO pacienteDTO) {
        if (pacienteDTO == null) return null;
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.getId());
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setApellido(pacienteDTO.getApellido());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setTelefono(pacienteDTO.getTelefono());
        paciente.setHistorialClinicoId(pacienteDTO.getHistorialClinicoId());
        return paciente;
    }

    @Override
    public RecetaDTO recetaToRecetaDTO(Receta receta) {
        if (receta == null) return null;
        RecetaDTO dto = new RecetaDTO();
        dto.setId(receta.getId());
        dto.setMedicamento(receta.getMedicamento());
        dto.setDosis(receta.getDosis());
        dto.setFechaEmision(receta.getFechaEmision());
        dto.setEstadoReceta(receta.getEstadoReceta());
        dto.setMedicoDTO(medicoToMedicoDTO(receta.getMedico()));
        dto.setPacienteDTO(pacienteToPacienteDTO(receta.getPaciente()));
        return dto;
    }

    @Override
    public Receta recetaDTOToReceta(RecetaDTO recetaDTO) {
        if (recetaDTO == null) return null;
        Receta receta = new Receta();
        receta.setId(recetaDTO.getId());
        receta.setMedicamento(recetaDTO.getMedicamento());
        receta.setDosis(recetaDTO.getDosis());
        receta.setFechaEmision(recetaDTO.getFechaEmision());
        receta.setEstadoReceta(recetaDTO.getEstadoReceta());
        // El médico y el paciente se asignarán en la capa de servicio
        return receta;
    }

    @Override
    public List<RecetaDTO> recetasToRecetasDTO(List<Receta> recetas) {
        return recetas.stream().map(this::recetaToRecetaDTO).collect(Collectors.toList());
    }
}
