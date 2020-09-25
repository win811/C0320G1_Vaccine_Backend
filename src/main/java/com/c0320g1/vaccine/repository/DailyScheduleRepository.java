package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule,Long> {
}
