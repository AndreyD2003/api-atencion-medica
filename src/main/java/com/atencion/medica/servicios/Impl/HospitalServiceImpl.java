package com.atencion.medica.servicios.Impl;

import com.atencion.medica.dtos.HistorialMedicoDTO;
import com.atencion.medica.dtos.MedicoDTO;
import com.atencion.medica.dtos.PacienteDTO;
import com.atencion.medica.dtos.RecetaDTO;
import com.atencion.medica.entidades.Medico;
import com.atencion.medica.entidades.Paciente;
import com.atencion.medica.entidades.Receta;
import com.atencion.medica.excepciones.MedicoNotFoundException;
import com.atencion.medica.excepciones.PacienteNotFoundException;
import com.atencion.medica.mappers.AtencionMedicaMapper;
import com.atencion.medica.repositorios.MedicoRepository;
import com.atencion.medica.repositorios.PacienteRepository;
import com.atencion.medica.repositorios.RecetaRepository;
import com.atencion.medica.servicios.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //spring
@Transactional //spring
@Slf4j //lombok
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private RecetaRepository recetaRepository;
    @Autowired
    private AtencionMedicaMapper mapper;

    @Override
    public MedicoDTO crearMedico(MedicoDTO medicoDTO) {
        // Lógica simple para dividir nombre completo
        String[] nombres = medicoDTO.getNombreCompleto().split(" ", 2);
        Medico medico = new Medico();
        medico.setNombre(nombres[0]);
        medico.setApellido(nombres.length > 1 ? nombres[1] : "");
        medico.setCedulaProfesional(medicoDTO.getCedulaProfesional());
        medico.setEspecialidad(medicoDTO.getEspecialidad());

        Medico medicoGuardado = medicoRepository.save(medico);
        return mapper.medicoToMedicoDTO(medicoGuardado);
    }

    @Override
    public List<MedicoDTO> obtenerTodosLosMedicos() {
        return medicoRepository.findAll()
                .stream()
                .map(mapper::medicoToMedicoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicoDTO obtenerMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException("Médico no encontrado con ID: " + id));
        return mapper.medicoToMedicoDTO(medico);
    }

    @Override
    public HistorialMedicoDTO obtenerHistorialMedico(String historialId, int page, int size) {
        // Primero, validamos que el paciente exista
        Optional<Paciente> pacienteOpt = pacienteRepository.findByHistorialClinicoId(historialId);
        if (pacienteOpt.isEmpty()) {
            throw new PacienteNotFoundException("No se encontró paciente con el ID de historial: " + historialId);
        }
        Paciente paciente = pacienteOpt.get();

        Pageable pageable = PageRequest.of(page, size);
        Page<Receta> recetasPaginadas = recetaRepository.findByPacienteHistorialClinicoId(historialId, pageable);

        List<RecetaDTO> recetasDTO = mapper.recetasToRecetasDTO(recetasPaginadas.getContent());

        HistorialMedicoDTO historialDTO = new HistorialMedicoDTO();
        historialDTO.setHistorialClinicoId(historialId);
        historialDTO.setNombrePaciente(paciente.getNombre() + " " + paciente.getApellido());
        historialDTO.setCurrentPage(recetasPaginadas.getNumber());
        historialDTO.setPageSize(recetasPaginadas.getSize());
        historialDTO.setTotalPages(recetasPaginadas.getTotalPages());
        historialDTO.setRecetasDTO(recetasDTO);

        return historialDTO;
    }

    @Override
    public PacienteDTO crearPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = mapper.pacienteDTOToPaciente(pacienteDTO);
        Paciente pacienteGuardado = pacienteRepository.save(paciente);
        return mapper.pacienteToPacienteDTO(pacienteGuardado);
    }

    @Override
    public RecetaDTO crearReceta(RecetaDTO recetaDTO) {
        Long medicoId = recetaDTO.getMedicoDTO().getId();
        Long pacienteId = recetaDTO.getPacienteDTO().getId();

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new MedicoNotFoundException("Médico no encontrado con ID: " + medicoId));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente no encontrado con ID: " + pacienteId));

        Receta receta = mapper.recetaDTOToReceta(recetaDTO);
        receta.setMedico(medico);
        receta.setPaciente(paciente);

        Receta recetaGuardada = recetaRepository.save(receta);
        return mapper.recetaToRecetaDTO(recetaGuardada);
    }
}
