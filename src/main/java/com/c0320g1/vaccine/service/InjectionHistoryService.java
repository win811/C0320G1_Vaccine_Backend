package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.dto.DailyScheduleDTO;
import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.InjectionHistory;
import org.springframework.data.domain.Page;

public interface InjectionHistoryService {

    void save (InjectionHistory injectionHistory);

    InjectionHistory findById(Long id);

    //    Quân
    Page<InjectionHistoryDTO> search(String fullName, String injected, int page);

}
