package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.DailySchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {

    // duc thong
    @Query(value = "select * from  vaccines inner join vaccine.daily_schedule on vaccines.vaccine_id= vaccine.daily_schedule.vaccine_id where vaccine_name like %?1% and vaccine_inventory_status like %?2% and vaccination_date between ?3 and ?4",nativeQuery = true)
    Page<DailySchedule> findAllBySearch(String nameVaccine, String status, String startDay, String endDay, Pageable pageable);
}
