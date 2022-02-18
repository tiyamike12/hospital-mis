package com.tiyamike.hospitalmanagementsystem.services;

import com.tiyamike.hospitalmanagementsystem.models.LabResult;
import com.tiyamike.hospitalmanagementsystem.models.PatientCase;
import com.tiyamike.hospitalmanagementsystem.repositories.PatientCaseRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PatientCaseService {
    private final PatientCaseRepository patientCaseRepository;

    public PatientCaseService(PatientCaseRepository patientCaseRepository) {
        this.patientCaseRepository = patientCaseRepository;
    }

    public void savePatientCase(PatientCase patientCase){
        patientCase.setCreatedAt(Instant.now());
        patientCaseRepository.save(patientCase);
    }

    public List<PatientCase> getPatientCases(){
        return patientCaseRepository.findAll();
    }

    public Optional<PatientCase> findById(long id){
        return patientCaseRepository.findById(id);
    }

    public void updatePatientCase(PatientCase patientCase){
        patientCaseRepository.save(patientCase);
    }

    public void deletePatientCase(long id){
        patientCaseRepository.deleteById(id);
    }
}
