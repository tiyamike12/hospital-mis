package com.tiyamike.hospitalmanagementsystem.repositories;

import com.tiyamike.hospitalmanagementsystem.models.LabResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabResultRepository extends JpaRepository<LabResult, Long> {
}
