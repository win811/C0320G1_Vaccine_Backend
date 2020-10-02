package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.model.DailySchedule;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.service.DailyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class DailyScheduleController {
    @Autowired
    private DailyScheduleService dailyScheduleService;


    @GetMapping("admin/dailySchedule")
    public Page<DailySchedule>listDailySchedule(@RequestParam(name = "nameVaccine",defaultValue = "") String nameVaccine,
                                                @RequestParam(name = "status",defaultValue = "")String status,
                                                @RequestParam(name = "startDay",defaultValue = "2020-01-01") String startDay,
                                                @RequestParam(name = "endDay",defaultValue = "2020-12-31")String endDay,
                                                @PageableDefault(value=5) Pageable pageable){
        return dailyScheduleService.findAllBySearch(nameVaccine,status,startDay,endDay,pageable);
    }

    @GetMapping("/admin/dailySchedule/listInject")
    public Page<InjectionHistory> listInject(@RequestParam(name = "id",defaultValue = "1") Long id,
                                             @PageableDefault(value=7) Pageable pageable){
        return dailyScheduleService.findAllInject(id,"định kì",pageable);
    }



}
