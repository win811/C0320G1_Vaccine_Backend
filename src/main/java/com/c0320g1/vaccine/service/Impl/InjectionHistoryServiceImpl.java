package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.dto.DailyScheduleDTO;
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



@Service
public class InjectionHistoryServiceImpl implements InjectionHistoryService {
    @Autowired
    InjectionHistoryRepository injectionHistoryRepository;

    Pageable pageableDefault = PageRequest.of(0,4);

    @Override
    public void save(InjectionHistory injectionHistory) {
        injectionHistoryRepository.save(injectionHistory);

    }

    @Override
    public InjectionHistory findById(Long id) {
        return injectionHistoryRepository.findById(id).orElse(null);
    }


    //    Quân
    @Override
    public Page<InjectionHistoryDTO> search(String fullName, String injected, int page) {
        if(page>0){
            Pageable pageable = PageRequest.of(--page,4);
            Page<InjectionHistory> injectionHistories =  injectionHistoryRepository.findByPatient_FullNameContainingAndIsInjectedContainingAndRegisterType(fullName,injected,"Định kỳ",pageable);
            return mapEntityPageIntoDtoPage(injectionHistories, InjectionHistoryDTO.class);
        }
        Page<InjectionHistory> injectionHistories =  injectionHistoryRepository.findByPatient_FullNameContainingAndIsInjectedContainingAndRegisterType(fullName,injected,"Định kỳ",pageableDefault);
        return mapEntityPageIntoDtoPage(injectionHistories, InjectionHistoryDTO.class);
    }

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
       ModelMapper modelMapper = new ModelMapper();
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }
}
