package com.atencion.medica.web;

import com.atencion.medica.servicios.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MedicoController {
    @Autowired
    private HospitalService pacienteService;

}
