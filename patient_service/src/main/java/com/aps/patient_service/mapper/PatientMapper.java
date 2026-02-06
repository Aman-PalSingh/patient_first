package com.aps.patient_service.mapper;

import com.aps.patient_service.dto.PatientRequestDTO;
import com.aps.patient_service.dto.PatientResponseDTO;
import com.aps.patient_service.model.Patient;

public class PatientMapper {
    public static Patient toPatient(PatientRequestDTO patientRequestDTO){
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfRegistration(patientRequestDTO.getDateOfRegistration());
        return patient;
    }
    public static PatientResponseDTO toPatientResponseDTO(Patient patient){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setDateOfRegistration(patient.getDateOfRegistration());
        return patientResponseDTO;

    }
}
