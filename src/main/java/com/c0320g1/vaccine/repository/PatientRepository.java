package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {
    //    CREATE BY ANH DUC
    Patient findByBirthdayAndPhoneNumber(LocalDate birthday, String phoneNumber);
    // Thành Long
    Page<Patient> findAllByStatusIsTrue(Pageable pageable);

    //Thành Long
    Patient findAllByIdAndStatusIsTrue(Long id);

    Boolean findByCode(String code);
}
