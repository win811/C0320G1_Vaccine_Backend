package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class InjectionHistoryController {
    @Autowired
    InjectionHistoryService injectionHistoryService;

    @GetMapping("/account/injection-history/{patientId}")
    public ResponseEntity<Page<InjectionHistory>> getAll (@PathVariable(value = "patientId") Long patientId,
                                                          @PageableDefault(value = 5) Pageable pageable){
        Page<InjectionHistory> injectionHistories = injectionHistoryService.findInjectionHistoryByPatientId(patientId, pageable);
        return ResponseEntity.ok(injectionHistories);
    }
}
