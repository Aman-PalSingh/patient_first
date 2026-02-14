package com.aps.patient_service.service;

import com.aps.patient_service.dto.PatientRequestDTO;
import com.aps.patient_service.dto.PatientResponseDTO;
import com.aps.patient_service.exception.EmailAlreadyExistsException;
import com.aps.patient_service.exception.PatientNotFoundException;
import com.aps.patient_service.mapper.PatientMapper;
import com.aps.patient_service.model.Patient;
import com.aps.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
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

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Patient with this email already exists " + patientRequestDTO.getEmail());
        }

        Patient patient = patientRepository.save(PatientMapper.toPatient(patientRequestDTO));
        return PatientMapper.toPatientResponseDTO(patient);

    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        //If patient not found
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient id not found "+ id));

        //Email is unique across users, but UPDATE logic must exclude the current user.
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Patient with this email already exists " + patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());
        Patient updatedPatient= patientRepository.save(patient);

        return PatientMapper.toPatientResponseDTO(updatedPatient);
    }
    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }
}






