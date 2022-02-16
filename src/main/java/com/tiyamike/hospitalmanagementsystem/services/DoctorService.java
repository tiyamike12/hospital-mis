package com.tiyamike.hospitalmanagementsystem.services;

import com.tiyamike.hospitalmanagementsystem.models.Doctor;
import com.tiyamike.hospitalmanagementsystem.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void saveDoctor(Doctor doctor){
        doctor.setCreatedAt(Instant.now());
        doctorRepository.save(doctor);
    }

    public List<Doctor> getDoctors(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findById(long id){
        return doctorRepository.findById(id);
    }

    public void updateDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(long id){
        doctorRepository.deleteById(id);
    }
}
