package com.tiyamike.hospitalmanagementsystem.repositories;

import com.tiyamike.hospitalmanagementsystem.models.PatientCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientCaseRepository extends JpaRepository<PatientCase, Long> {
}
