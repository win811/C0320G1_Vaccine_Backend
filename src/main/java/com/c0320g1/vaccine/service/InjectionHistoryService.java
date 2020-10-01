package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.InjectionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InjectionHistoryService {

    // Thành Long
    void save (InjectionHistory injectionHistory);

    // Thành Long
    InjectionHistory findById(Long id);

    // Thành Long
    Page<InjectionHistory> findAll(Pageable pageable);

    // Thành Long
    Page<InjectionHistory> findInjectionHistoryByPatientId(Long patientId, Pageable pageable);
}
