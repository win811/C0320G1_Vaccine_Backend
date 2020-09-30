package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.InjectionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InjectionHistoryService {

    void save (InjectionHistory injectionHistory);

    InjectionHistory findById(Long id);

    Page<InjectionHistory> findAll(Pageable pageable);

    Page<InjectionHistory> findInjectionHistoryByPatientId(Long patientId, Pageable pageable);
}
