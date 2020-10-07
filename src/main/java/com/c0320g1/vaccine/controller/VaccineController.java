package com.c0320g1.vaccine.controller;
import com.c0320g1.vaccine.dto.VaccineListDTO;
import com.c0320g1.vaccine.model.Vaccine;
import com.c0320g1.vaccine.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    // Cường
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

    // Cường
    @PostMapping("/import-vaccine")
    public ResponseEntity<Vaccine> importVaccine(@RequestBody Vaccine vaccine) {
        return ResponseEntity.ok(vaccineService.save(vaccine));
    }

    // Cường
    @PutMapping("/export-vaccine")
    public ResponseEntity<Vaccine> exportVaccine(@RequestParam Long id,@RequestParam Integer exportAmount) {
        Vaccine vaccine = vaccineService.findById(id);
        if (vaccine == null) {
            return ResponseEntity.notFound().build();
        }
        vaccine.setAmount(vaccine.getAmount() - exportAmount);
        return ResponseEntity.ok(vaccineService.save(vaccine));
    }

    // Thành Long
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

    // Thành Long
    @GetMapping("/admin/vaccine/{id}")
    public ResponseEntity<Vaccine> getVaccineById(@PathVariable(value = "id") Long id){
        Vaccine vaccine = vaccineService.findById(id);
        return ResponseEntity.ok().body(vaccine);
    }

    // Thành Long
    @PutMapping("/admin/vaccine-list/update")
    public ResponseEntity<Vaccine> updateVaccine(@RequestBody Vaccine vaccineUpdate){
        vaccineService.update(vaccineUpdate);
        return ResponseEntity.ok(vaccineUpdate);
    }

}
