package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.InjectionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InjectionHistoryRepository extends JpaRepository<InjectionHistory, Long> {
}
