package com.tiyamike.hospitalmanagementsystem.services;

import com.tiyamike.hospitalmanagementsystem.models.Gender;
import com.tiyamike.hospitalmanagementsystem.models.Nurse;
import com.tiyamike.hospitalmanagementsystem.models.Patient;
import com.tiyamike.hospitalmanagementsystem.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void savePatient(Patient patient){
        patient.setCreatedAt(Instant.now());
        patientRepository.save(patient);
    }

    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(long id){
        return patientRepository.findById(id);
    }

    public void updatePatient(Patient patient){
        patientRepository.save(patient);
    }

    public void deletePatient(long id){
        patientRepository.deleteById(id);
    }

}
