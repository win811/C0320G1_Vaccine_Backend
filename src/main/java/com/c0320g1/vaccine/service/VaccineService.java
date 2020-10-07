package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.dto.VaccineListDTO;
import com.c0320g1.vaccine.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface VaccineService {
    // Cường
    Page<Vaccine> findBySpec(String name, String category, String country, String inventoryStatus, int page);
    Vaccine save(Vaccine vaccine);
    Vaccine findById(Long id);
    List<Vaccine> getAllVaccine();

    //Thành Long
    Page<VaccineListDTO> findVaccineByCriteria(Specification<Vaccine> spec, int page);

    //Thành Long
    Specification<Vaccine> getFilter(String code, String category, String country, String minPrice, String maxPrice);

    //Thành Long
    Page<VaccineListDTO> findAllVaccine(int page);

    //Thành Long
    void update(Vaccine vaccine);
}
