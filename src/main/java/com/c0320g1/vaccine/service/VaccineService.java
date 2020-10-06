package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.Vaccine;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VaccineService {

    // Cường
    Page<Vaccine> findBySpec(String name,String category, String country, String inventoryStatus,int page);
    Vaccine save(Vaccine vaccine);
    Vaccine findById(Long id);
    List<Vaccine> getAllVaccine();
}
