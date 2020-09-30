package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.dto.VaccineListDTO;
import com.c0320g1.vaccine.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface VaccineService {
    Vaccine save(Vaccine vaccine);
    Vaccine findById(Long id);

    //Thành Long
    Page<VaccineListDTO> findVaccineByCriteria(Specification<Vaccine> spec, int page);

    //Thành Long
    Specification<Vaccine> getFilter(String code, String category, String country, String minPrice, String maxPrice);

    //Thành Long
    Page<VaccineListDTO> findAllVaccine(int page);

    //Thành Long
    void update(Vaccine vaccine);
}
