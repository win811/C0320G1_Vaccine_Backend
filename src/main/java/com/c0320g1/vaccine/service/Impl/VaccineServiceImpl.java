package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.Vaccine;
import com.c0320g1.vaccine.repository.VaccineRepository;
import com.c0320g1.vaccine.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public Vaccine save(Vaccine vaccine) {
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine findById(Long id) {
        return vaccineRepository.findById(id).orElse(null);
    }

    /**
     * Creator: Duy
     */
    @Override
    public List<Vaccine> getAllVaccine() {
        return vaccineRepository.findAll();
    }


}
