package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.InjectionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InjectionHistoryService {

    void save (InjectionHistory injectionHistory);

    InjectionHistory findById(Long id);

    //    Quân
    Page<InjectionHistoryDTO> search(String fullName, String injected, int page);

    // thong
    Page<InjectionHistory> findAllBySearch(String namePatient, String isInject, String type, Pageable pageable);
}
