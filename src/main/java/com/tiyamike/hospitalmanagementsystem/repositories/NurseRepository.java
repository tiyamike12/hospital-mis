package com.tiyamike.hospitalmanagementsystem.repositories;

import com.tiyamike.hospitalmanagementsystem.models.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {
}
