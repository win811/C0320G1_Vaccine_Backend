package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.InfectionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfectionStateRepository extends JpaRepository<InfectionState, Long> {
}
