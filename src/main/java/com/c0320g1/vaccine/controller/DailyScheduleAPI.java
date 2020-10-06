package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.model.DailySchedule;
import com.c0320g1.vaccine.service.DailyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class DailyScheduleAPI {
    @Autowired
    DailyScheduleService dailyScheduleService;

    //An
    @GetMapping("dailyschedule")
    public ResponseEntity<Page<DailySchedule>> getAllDailySchedule(@PageableDefault(value = 5) Pageable pageable){
        Page<DailySchedule> dailySchedules =  dailyScheduleService.findAll(pageable);
        return ResponseEntity.ok(dailySchedules);
    }

    //An
    @GetMapping("info-vaccine/{id}")
    public ResponseEntity<DailySchedule> getInfoVaccine(@PathVariable long id){
        DailySchedule dailySchedules =  dailyScheduleService.findById(id);
        return ResponseEntity.ok(dailySchedules);
    }


}
