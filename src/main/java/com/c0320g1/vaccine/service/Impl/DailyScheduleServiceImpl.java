package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.DailySchedule;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.model.Patient;
import com.c0320g1.vaccine.repository.DailyScheduleRepository;
import com.c0320g1.vaccine.repository.InjectionHistoryRepository;
import com.c0320g1.vaccine.model.Contact;
import com.c0320g1.vaccine.model.DailySchedule;
import com.c0320g1.vaccine.repository.DailyScheduleRepository;
import com.c0320g1.vaccine.service.DailyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


//duc thong
@Service
public class DailyScheduleServiceImpl implements DailyScheduleService {
    @Autowired
    private DailyScheduleRepository dailyScheduleRepository;

    @Autowired
    private InjectionHistoryRepository injectionHistoryRepository;

    @Override
    public Page<DailySchedule> findAllBySearch(String nameVaccine, String status, String startDay, String endDay, Pageable pageable) {
        return dailyScheduleRepository.findAllBySearch(nameVaccine,status,startDay,endDay,pageable);
    }

    @Override
    public List<DailySchedule> findAll() {
        return dailyScheduleRepository.findAll();
    }

    @Override
    public Page<InjectionHistory> findAllInject(Long id,String type, Pageable pageable) {
        return injectionHistoryRepository.findAllByVaccine_IdAndRegisterType(id,type,pageable);
    }
    @Override
    public void save(DailySchedule dailySchedule) {
        dailyScheduleRepository.save(dailySchedule);
    }

    //An
    @Override
    public Page<DailySchedule> findAll(Pageable pageable) {
        return dailyScheduleRepository.findAll(pageable);
    }

    //An
    @Override
    public DailySchedule findById(long id) {
        return dailyScheduleRepository.findById(id).orElse(null);
    }
}
