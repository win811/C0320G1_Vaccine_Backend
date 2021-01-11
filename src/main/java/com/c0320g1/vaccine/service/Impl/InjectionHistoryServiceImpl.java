package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.dto.InjectionHistoryDTO;
import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.repository.InjectionHistoryRepository;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InjectionHistoryServiceImpl implements InjectionHistoryService {
    @Autowired
    InjectionHistoryRepository injectionHistoryRepository;

    Pageable pageableDefault = PageRequest.of(0, 4);

    //Cường
    @Override
    public List<InjectionHistory> findAll() {
        return this.injectionHistoryRepository.findAll();
    }

    //    Quân
    @Override
    public Page<InjectionHistoryDTO> search(String fullName, String injected, int page) {
        if (page > 0) {
            Pageable pageable = PageRequest.of(--page, 4);
            Page<InjectionHistory> injectionHistories = injectionHistoryRepository.findByPatient_FullNameContainingAndIsInjectedContainingAndRegisterType(fullName, injected, "Định kỳ", pageable);
            return mapEntityPageIntoDtoPage(injectionHistories, InjectionHistoryDTO.class);
        }
        Page<InjectionHistory> injectionHistories = injectionHistoryRepository.findByPatient_FullNameContainingAndIsInjectedContainingAndRegisterType(fullName, injected, "Định kỳ", pageableDefault);
        return mapEntityPageIntoDtoPage(injectionHistories, InjectionHistoryDTO.class);
    }

    //thong
    @Override
    public Page<InjectionHistory> findAllBySearch(String namePatient, String isInject, String type, Pageable pageable) {
        return injectionHistoryRepository.findByPatient_FullNameContainingAndIsInjectedAndRegisterType(namePatient,isInject,type,pageable);
    }

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }

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
    public Page<InjectionHistory> findInjectionHistoryByAccountId(Long accountId, Pageable pageable) {
        return injectionHistoryRepository.findByAccount_Id(accountId, pageable);
    }
}
