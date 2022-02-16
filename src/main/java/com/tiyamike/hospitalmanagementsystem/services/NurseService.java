package com.tiyamike.hospitalmanagementsystem.services;

import com.tiyamike.hospitalmanagementsystem.models.Nurse;
import com.tiyamike.hospitalmanagementsystem.repositories.NurseRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class NurseService {
    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public void saveNurse(Nurse nurse){
        nurse.setCreatedAt(Instant.now());
        nurseRepository.save(nurse);
    }

    public List<Nurse> getNurses(){
        return nurseRepository.findAll();
    }

    public Optional<Nurse> findById(long id){
        return nurseRepository.findById(id);
    }

    public void updateNurse(Nurse nurse){
        nurseRepository.save(nurse);
    }

    public void deleteNurse(long id){
        nurseRepository.deleteById(id);
    }
}
