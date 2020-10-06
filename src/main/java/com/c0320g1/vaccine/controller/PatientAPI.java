package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.model.Patient;
import com.c0320g1.vaccine.repository.PatientRepository;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import com.c0320g1.vaccine.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PatientAPI {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;

    //AN - Thêm benh nhan tiem dịnh kỳ
    @PostMapping("/create-patient")
    public ResponseEntity<Patient> createPatiendt(@RequestBody Patient patient) {
        List<Patient> patients = patientRepository.findAll();
        Patient patientCode = patients.get(patients.size() - 1);
        if (patientService.chekedPatient(patient)) {
            patient.setCode(patientCode.getId());
            patientService.save(patient);
            return ResponseEntity.ok(patient);
        }
        Patient paten2 = patientRepository.findByBirthdayAndPhoneNumber(patient.getBirthday(), patient.getPhoneNumber());
        return ResponseEntity.ok(paten2);
    }
}
