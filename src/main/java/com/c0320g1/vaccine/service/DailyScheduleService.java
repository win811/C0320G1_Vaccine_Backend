package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.DailySchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DailyScheduleService {
    void save(DailySchedule dailySchedule);
    Page<DailySchedule> findAll(Pageable pageable);
}
