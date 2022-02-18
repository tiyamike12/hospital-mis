package com.tiyamike.hospitalmanagementsystem.services;

import com.tiyamike.hospitalmanagementsystem.models.LabResult;
import com.tiyamike.hospitalmanagementsystem.models.Nurse;
import com.tiyamike.hospitalmanagementsystem.repositories.LabResultRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class LabResultService {
    private final LabResultRepository labResultRepository;

    public LabResultService(LabResultRepository labResultRepository) {
        this.labResultRepository = labResultRepository;
    }

    public void saveLabResult(LabResult labResult){
        labResult.setCreatedAt(Instant.now());
        labResultRepository.save(labResult);
    }

    public List<LabResult> getLabResults(){
        return labResultRepository.findAll();
    }

    public Optional<LabResult> findById(long id){
        return labResultRepository.findById(id);
    }

    public void updateLabResult(LabResult labResult){
        labResultRepository.save(labResult);
    }

    public void deleteLabResult(long id){
        labResultRepository.deleteById(id);
    }
}
