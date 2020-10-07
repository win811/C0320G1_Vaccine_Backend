package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.InjectionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface InjectionHistoryService {

    // Thành Long
    void save (InjectionHistory injectionHistory);

    // Thành Long
    InjectionHistory findById(Long id);

    //    Quân
//    Page<InjectionHistoryDTO> search(String fullName, String injected, int page);


    Page<InjectionHistoryDTO> search(String fullName, String injected, int page);

    // thong
    Page<InjectionHistory> findAllBySearch(String namePatient, String isInject, String type, Pageable pageable);

    // Thành Long
    Page<InjectionHistory> findAll(Pageable pageable);

    // Thành Long
    Page<InjectionHistory> findInjectionHistoryByAccountId(Long accountId, Pageable pageable);
}
