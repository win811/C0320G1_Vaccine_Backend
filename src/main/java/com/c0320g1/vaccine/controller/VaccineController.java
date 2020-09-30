package com.c0320g1.vaccine.controller;
import com.c0320g1.vaccine.model.Vaccine;
import com.c0320g1.vaccine.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/vaccine-storage")
    public ResponseEntity<Page<Vaccine>> getVaccineStorage(@RequestParam(defaultValue = "") String name,
                                                           @RequestParam(defaultValue = "") String category,
                                                           @RequestParam(defaultValue = "") String country,
                                                           @RequestParam(defaultValue = "") String inventoryStatus,
                                                           @RequestParam(defaultValue = "0") int page
                                                           ) {
        Page<Vaccine> vaccinePage = this.vaccineService.findBySpec(name,category,country,inventoryStatus,page);
        return ResponseEntity.ok(vaccinePage);
    }

    @PostMapping("/import-vaccine")
    public ResponseEntity<Vaccine> importVaccine(@RequestBody Vaccine vaccine) {
        return ResponseEntity.ok(vaccineService.save(vaccine));
    }

    @PutMapping("/export-vaccine")
    public ResponseEntity<Vaccine> exportVaccine(@RequestParam Long id,@RequestParam Integer exportAmount) {
        Vaccine vaccine = vaccineService.findById(id);
        if (vaccine == null) {
            return ResponseEntity.notFound().build();
        }
        vaccine.setAmount(vaccine.getAmount() - exportAmount);
        return ResponseEntity.ok(vaccineService.save(vaccine));
    }



}
