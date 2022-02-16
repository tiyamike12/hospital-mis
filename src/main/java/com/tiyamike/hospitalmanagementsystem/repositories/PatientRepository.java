package com.tiyamike.hospitalmanagementsystem.repositories;

import com.tiyamike.hospitalmanagementsystem.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
