package com.aps.patient_service.controller;

import com.aps.patient_service.dto.PatientRequestDTO;
import com.aps.patient_service.dto.PatientResponseDTO;
import com.aps.patient_service.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> allPatients = patientService.getPatients();
        return ResponseEntity.ok(allPatients);

    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO =  patientService.createPatient(patientRequestDTO);
        return new ResponseEntity<>(patientResponseDTO, HttpStatus.CREATED);
    }
}
