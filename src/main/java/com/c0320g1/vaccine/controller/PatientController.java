package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.dto.PatientListDTO;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.model.Patient;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import com.c0320g1.vaccine.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PatientController {
    @Autowired
    InjectionHistoryService injectionHistoryService;

    @Autowired
    PatientService patientService;

    @GetMapping("admin/patientInjectRequest")
    public Page<InjectionHistory> listDailySchedule(@RequestParam(name = "namePatient", defaultValue = "") String namePatient,
                                                    @RequestParam(name = "isInject", defaultValue = "chưa tiêm") String isInject,
                                                    @PageableDefault(value = 5) Pageable pageable) {
        return injectionHistoryService.findAllBySearch(namePatient, isInject, "yêu cầu", pageable);
    }

    @GetMapping("/admin/patientInjectRequest/{id}")
    public ResponseEntity<InjectionHistory> getPatientById(@PathVariable(value = "id") Long id) {
        InjectionHistory injectionHistory = injectionHistoryService.findById(id);
        return ResponseEntity.ok().body(injectionHistory);
    }

    @PutMapping("/admin/patientInjectRequest/{id}")
    public ResponseEntity<InjectionHistory> updateInjectionHistoryById(@PathVariable(value = "id") Long id) {
        InjectionHistory injectionHistory = injectionHistoryService.findById(id);
        if (injectionHistory == null) {
            System.out.println("null");
        } else {
            injectionHistory.setIsInjected("đã tiêm");
            injectionHistoryService.save(injectionHistory);
        }
        return ResponseEntity.ok().body(injectionHistory);
    }

    // Thành Long
    @GetMapping("/admin/patient-list")
    public ResponseEntity<Page<PatientListDTO>> getAllPatient(@RequestParam(name = "code", defaultValue = "") String code,
                                                              @RequestParam(name = "fullName", defaultValue = "") String fullName,
                                                              @RequestParam(name = "page", defaultValue = "1") int page) {
        Specification<Patient> specs = patientService.getFilter(code, fullName);
        Page<PatientListDTO> patients;
        if (specs != null) {
            patients = patientService.findPatientByCriteria(specs, page);
        } else {
            patients = patientService.findAllPatient(page);
        }

        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patients);
    }

    // Thành Long
    @PutMapping("/admin/patient-list/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
        patientService.update(patient);
        return ResponseEntity.ok().body(patient);
    }

    // Thành Long
    @PostMapping("/admin/patient-list/create")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        patientService.update(patient);
        return ResponseEntity.ok(patient);
    }

    // Thành Long
    @DeleteMapping("/admin/patient-list/delete/{id}")
    public ResponseEntity<Void> removePatient(@PathVariable(name = "id") Long id) {
        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

