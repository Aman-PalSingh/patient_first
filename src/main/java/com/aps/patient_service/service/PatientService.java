package com.aps.patient_service.service;

import com.aps.patient_service.dto.PatientRequestDTO;
import com.aps.patient_service.dto.PatientResponseDTO;
import com.aps.patient_service.mapper.PatientMapper;
import com.aps.patient_service.model.Patient;
import com.aps.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> allPatients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOs = allPatients.stream().map(PatientMapper::toPatientResponseDTO).collect(Collectors.toList());
//      List<PatientResponseDTO> patientResponseDTOs = allPatients.stream().map(patient -> PatientMapper.toPatientResponseDTO(patient)).toList();
        return patientResponseDTOs;

    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository.save(PatientMapper.toPatient(patientRequestDTO));
        return PatientMapper.toPatientResponseDTO(patient);

    }

}






