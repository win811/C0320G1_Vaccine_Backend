package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PatientRepository extends JpaRepository<Patient, Long> {
//    CREATE BY ANH DUC
    Patient findByBirthdayAndPhoneNumber(LocalDate birthday, String phoneNumber);
}
