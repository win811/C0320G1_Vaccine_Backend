package com.c0320g1.vaccine.service.Impl;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.repository.InjectionHistoryRepository;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InjectionHistorySerivceImpl implements InjectionHistoryService {
    @Autowired
    InjectionHistoryRepository injectionHistoryRepository;

    // Thành Long
    @Override
    public void save(InjectionHistory injectionHistory) {
        injectionHistoryRepository.save(injectionHistory);
    }

    // Thành Long
    @Override
    public InjectionHistory findById(Long id) {
        return injectionHistoryRepository.findById(id).orElse(null);
    }

    // Thành Long
    @Override
    public Page<InjectionHistory> findAll(Pageable pageable) {
        return injectionHistoryRepository.findAll(pageable);
    }

    // Thành Long
    @Override
    public Page<InjectionHistory> findInjectionHistoryByPatientId(Long patientId, Pageable pageable) {
        return injectionHistoryRepository.findByPatient_Id(patientId, pageable);
    }
}
