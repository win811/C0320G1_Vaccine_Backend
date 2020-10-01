package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
