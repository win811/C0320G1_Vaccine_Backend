package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.model.DailySchedule;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PatientController {
    @Autowired
    InjectionHistoryService injectionHistoryService;

    @GetMapping("admin/patientInjectRequest")
    public Page<InjectionHistory> listDailySchedule(@RequestParam(name = "namePatient",defaultValue = "") String namePatient,
                                                    @RequestParam(name = "isInject",defaultValue = "chưa tiêm")String isInject,
                                                    @PageableDefault(value=5) Pageable pageable){
        return injectionHistoryService.findAllBySearch(namePatient,isInject,"yêu cầu", pageable);
    }

    @GetMapping("/admin/patientInjectRequest/{id}")
    public ResponseEntity<InjectionHistory> getPatientById(@PathVariable(value = "id")Long id){
        InjectionHistory injectionHistory = injectionHistoryService.findById(id);
        return ResponseEntity.ok().body(injectionHistory);
    }

    @PutMapping("/admin/patientInjectRequest/{id}")
    public ResponseEntity<InjectionHistory> updateInjectionHistoryById(@PathVariable(value = "id")Long id){
        InjectionHistory injectionHistory = injectionHistoryService.findById(id);
        if (injectionHistory == null){
            System.out.println("null");
        }else {
            injectionHistory.setIsInjected("đã tiêm");
            injectionHistoryService.save(injectionHistory);
        }
        return ResponseEntity.ok().body(injectionHistory);
    }

}
