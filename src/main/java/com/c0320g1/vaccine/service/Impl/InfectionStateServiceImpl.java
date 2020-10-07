package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.InfectionState;
import com.c0320g1.vaccine.repository.InfectionStateRepository;
import com.c0320g1.vaccine.service.InfectionStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Creator: Duy
 */

@Service
public class InfectionStateServiceImpl implements InfectionStateService {


    @Autowired
    private InfectionStateRepository infectionStateRepository;

    @Override
    public List<InfectionState> getStatesInLocal() {
        return infectionStateRepository.findAll();
    }
}
