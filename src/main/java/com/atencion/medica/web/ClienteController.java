package com.atencion.medica.web;

import com.atencion.medica.dtos.HistorialMedicoDTO;
import com.atencion.medica.dtos.MedicoDTO;
import com.atencion.medica.dtos.RecetaDTO;
import com.atencion.medica.servicios.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private HospitalService medicoService;

    @GetMapping("/{historialId}/historial")
    public ResponseEntity<HistorialMedicoDTO> getHistorialMedico(
            @PathVariable String historialId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        HistorialMedicoDTO historial = medicoService.obtenerHistorialMedico(historialId, page, size);
        return ResponseEntity.ok(historial);
    }

}
