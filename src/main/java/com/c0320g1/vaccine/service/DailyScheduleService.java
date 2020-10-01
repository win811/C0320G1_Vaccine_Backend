package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.DailySchedule;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.model.Patient;
import com.c0320g1.vaccine.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DailyScheduleService {
    Page<DailySchedule> findAllBySearch(String nameVaccine, String status, String startDay, String endDay, Pageable pageable);
    List<DailySchedule> findAll();
    Page<InjectionHistory> findAllInject(Long id,String type, Pageable pageable);
}
