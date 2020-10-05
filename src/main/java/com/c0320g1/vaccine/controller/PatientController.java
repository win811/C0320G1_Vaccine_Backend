package com.c0320g1.vaccine.controller;
import com.c0320g1.vaccine.dto.PatientListDTO;
import com.c0320g1.vaccine.model.Patient;
import com.c0320g1.vaccine.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PatientController {
    @Autowired
    PatientService patientService;

    // Thành Long
    @GetMapping("/admin/patient-list")
    public ResponseEntity<Page<PatientListDTO>> getAllPatient(@RequestParam(name = "code", defaultValue = "") String code,
                                                              @RequestParam(name = "fullName", defaultValue = "") String fullName,
                                                              @RequestParam(name = "page", defaultValue = "1") int page) {
        Specification<Patient> specs = patientService.getFilter(code,fullName);
        Page<PatientListDTO> patients;
        if (specs != null) {
            patients =  patientService.findPatientByCriteria(specs, page);
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
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        patientService.update(patient);
        return ResponseEntity.ok().body(patient);
    }

    // Thành Long
    @PostMapping("/admin/patient-list/create")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        patientService.update(patient);
        return ResponseEntity.ok(patient);
    }

    // Thành Long
    @DeleteMapping("/admin/patient-list/delete/{id}")
    public ResponseEntity<Void> removePatient(@PathVariable(name = "id") Long id){
        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
