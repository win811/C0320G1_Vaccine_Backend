package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Long>, JpaSpecificationExecutor<Vaccine> {
}
