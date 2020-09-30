package com.c0320g1.vaccine.controller;

import com.c0320g1.vaccine.dto.VaccineListDTO;
import com.c0320g1.vaccine.model.Vaccine;
import com.c0320g1.vaccine.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class VaccineController {
    @Autowired
    VaccineService vaccineService;

    @GetMapping("/admin/vaccine-list")
    public ResponseEntity<Page<VaccineListDTO>> getAllVaccine(@RequestParam(name = "code", defaultValue = "") String code,
                                                              @RequestParam(name = "category", defaultValue = "") String category,
                                                              @RequestParam(name = "country", defaultValue = "") String country,
                                                              @RequestParam(name = "minPrice", defaultValue = "") String minPrice,
                                                              @RequestParam(name = "maxPrice", defaultValue = "") String maxPrice,
                                                              @RequestParam(name = "page", defaultValue = "1") int page) {
        Specification<Vaccine> specs = vaccineService.getFilter(code, category, country, minPrice, maxPrice);
        Page<VaccineListDTO> vaccines;
        if (specs != null) {
            vaccines =  vaccineService.findVaccineByCriteria(specs, page);
        } else {
            vaccines = vaccineService.findAllVaccine(page);
        }

        if (vaccines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vaccines);
    }

    @GetMapping("/admin/vaccine/{id}")
    public ResponseEntity<Vaccine> getVaccineById(@PathVariable(value = "id") Long id){
        Vaccine vaccine = vaccineService.findById(id);
        return ResponseEntity.ok().body(vaccine);
    }

    @PutMapping("/admin/vaccine-list/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable(value = "id") Long id,
                                                 @RequestBody Vaccine vaccineUpdate){
        Vaccine vaccine = vaccineService.findById(id);
        vaccine.setPrice(vaccineUpdate.getPrice());
        vaccineService.update(vaccine);
        return ResponseEntity.ok().body(vaccine);
    }
}
