package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.InfectionState;

import java.util.List;

public interface InfectionStateService {
    List<InfectionState> getStatesInLocal();
}
