package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.Vaccine;

import java.util.List;

public interface VaccineService {
    Vaccine save(Vaccine vaccine);
    Vaccine findById(Long id);
    List<Vaccine> getAllVaccine();
}
