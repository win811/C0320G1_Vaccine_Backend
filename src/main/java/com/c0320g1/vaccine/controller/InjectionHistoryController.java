package com.c0320g1.vaccine.controller;
import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.*;
import com.c0320g1.vaccine.repository.AccountRepository;
import com.c0320g1.vaccine.repository.PatientRepository;
import com.c0320g1.vaccine.repository.VaccineRepository;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class InjectionHistoryController {

    @Autowired
    InjectionHistoryService injectionHistoryService;

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AccountRepository accountRepository;

    //    Quân
//    @GetMapping("/injection-list")
//    public ResponseEntity<Page<InjectionHistoryDTO>> getListInjected(@RequestParam(name = "fullName", defaultValue = "") String fullName,
//                                                                     @RequestParam(name = "injected", defaultValue = "") String injected,
//                                                                     @RequestParam(name = "page", defaultValue = "0") int page) {
//        Page<InjectionHistoryDTO> injectionHistoryDTO = injectionHistoryService.search(fullName, injected, page);
//        if (injectionHistoryDTO.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return ResponseEntity.ok(injectionHistoryDTO);
//    }

    // Thành Long
    @GetMapping("/account/injection-history/{accountId}")
    public ResponseEntity<Page<InjectionHistory>> getAllInjectionHistoryByAccountId (@PathVariable(value = "accountId") Long accountId,
                                                          @PageableDefault(value = 5) Pageable pageable){
        Page<InjectionHistory> injectionHistories = injectionHistoryService.findInjectionHistoryByAccountId(accountId, pageable);
        return ResponseEntity.ok(injectionHistories);
    }

    //Tùng
    @GetMapping("/account/injection-history/get/{id}")
    public ResponseEntity<InjectionHistory> getInjectionHistory(@PathVariable Long id){
        InjectionHistory injectionHistory = injectionHistoryService.findById(id);
        if(injectionHistory == null){
            return new ResponseEntity<InjectionHistory>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<InjectionHistory>(injectionHistory, HttpStatus.OK);
    }

    //Tùng
    @PutMapping("/account/injection-history/reply/{id}")
    public ResponseEntity<InjectionHistory> replyInjection(@PathVariable Long id, @RequestBody InjectionHistory injectionHistory){
        InjectionHistory injection = injectionHistoryService.findById(id);
        injection.setResponseContent(injectionHistory.getResponseContent());
        injectionHistoryService.save(injection);
        return ResponseEntity.ok().body(injection);
    }

    @GetMapping("/account/injection-history/vaccine")
    public ResponseEntity<List<Vaccine>> getApiOfVaccine() {
        List<Vaccine> vaccinesList = vaccineRepository.findAll();
        return new ResponseEntity<List<Vaccine>>(vaccinesList, HttpStatus.OK);
    }

    @GetMapping("/account/injection-history/account")
    public ResponseEntity<List<Account>> getApiOfAccount() {
        List<Account> accountList = accountRepository.findAll();
        return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
    }
    @GetMapping("/account/injection-history/patient")
    public ResponseEntity<List<Patient>> getApiOfPatient() {
        List<Patient> patientsList = patientRepository.findAll();
        return new ResponseEntity<List<Patient>>(patientsList, HttpStatus.OK);
    }
}


