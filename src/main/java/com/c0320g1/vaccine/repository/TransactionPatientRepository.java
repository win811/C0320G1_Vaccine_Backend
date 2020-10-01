package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.TransactionPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionPatientRepository extends JpaRepository<TransactionPatient, Long>, JpaSpecificationExecutor<TransactionPatient> {
}
