package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.model.Patient;
import com.c0320g1.vaccine.repository.AccountRepository;
import com.c0320g1.vaccine.repository.PatientRepository;
import com.c0320g1.vaccine.repository.VaccineRepository;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import com.c0320g1.vaccine.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class InjectionHistoryController {

    @Autowired
    InjectionHistoryService injectionHistoryService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    AccountRepository accountRepository;

    //    Quân
    @GetMapping("/injection-list")
    public ResponseEntity<Page<InjectionHistoryDTO>> getListInjected(@RequestParam(name = "fullName", defaultValue = "") String fullName,
                                                                     @RequestParam(name = "injected", defaultValue = "") String injected,
                                                                     @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<InjectionHistoryDTO> injectionHistoryDTO = injectionHistoryService.search(fullName, injected, page);
        if (injectionHistoryDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(injectionHistoryDTO);
    }

    //An
    @PostMapping("/injection-add")
    public ResponseEntity<InjectionHistory> addInjectionHistory(@RequestBody InjectionHistory injectionHistory) {
        injectionHistoryService.save(injectionHistory);
        return ResponseEntity.ok(injectionHistory);
    }

    //An
    @GetMapping("/injection-add-history")
    public ResponseEntity<InjectionHistory> register(@RequestParam(value = "id") Long id,
                                                     @RequestParam(value = "vaccineId") Long vaccineId,
                                                     @RequestParam(value = "accountId") Long accountId,
                                                     @RequestParam(value = "injectionDate") String injectionDate) {
        InjectionHistory injectionHistory1 = new InjectionHistory();
        LocalDate dayNew = LocalDate.parse(injectionDate);
        LocalTime timenew = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.of(dayNew, timenew);

        injectionHistory1.setPatient(patientRepository.findById(id).orElse(null));
        injectionHistory1.setVaccine(vaccineRepository.findById(vaccineId).orElse(null));
        injectionHistory1.setAccount(accountRepository.findById(accountId).orElse(null));
        injectionHistory1.setRegisterType("định kỳ");
        injectionHistory1.setResponseContent("Chưa xác định");
        injectionHistory1.setIsInjected("chưa tiêm");
        injectionHistory1.setInjectionDate(dateTime);
        injectionHistoryService.save(injectionHistory1);
        return ResponseEntity.ok(injectionHistory1);
    }


}

