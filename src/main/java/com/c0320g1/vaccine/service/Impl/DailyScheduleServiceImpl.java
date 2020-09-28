package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.DailySchedule;
import com.c0320g1.vaccine.repository.DailyScheduleRepository;
import com.c0320g1.vaccine.service.DailyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DailyScheduleServiceImpl implements DailyScheduleService {
    @Autowired
    DailyScheduleRepository dailyScheduleRepository;

    @Override
    public void save(DailySchedule dailySchedule) {
        dailyScheduleRepository.save(dailySchedule);
    }

    //An
    @Override
    public Page<DailySchedule> findAll(Pageable pageable) {
        return dailyScheduleRepository.findAll(pageable);
    }
}
