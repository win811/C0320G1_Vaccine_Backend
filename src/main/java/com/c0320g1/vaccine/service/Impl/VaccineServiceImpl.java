package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.Vaccine;
import com.c0320g1.vaccine.repository.VaccineRepository;
import com.c0320g1.vaccine.service.VaccineService;
import com.c0320g1.vaccine.service.search.VaccineSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public Page<Vaccine> findBySpec(String name, String category, String country, String inventoryStatus, int page) {
        Sort sort = Sort.by(Sort.Direction.ASC,"expiryDate");
        Pageable pageable = PageRequest.of(page - 1,2,sort);
        Specification<Vaccine> specVaccine = null;
        List<VaccineSpecification> vaccineSpecList = new ArrayList<>();
        vaccineSpecList.add(new VaccineSpecification("name","like",name));
        vaccineSpecList.add(new VaccineSpecification("category","like",category));
        vaccineSpecList.add(new VaccineSpecification("country","like",country));
        vaccineSpecList.add(new VaccineSpecification("inventoryStatus","like",inventoryStatus));
        if (!vaccineSpecList.isEmpty()) {
            specVaccine = Specification.where(vaccineSpecList.get(0));
            for (int i = 1; i < vaccineSpecList.size(); i++) {
                assert specVaccine != null;
                specVaccine = specVaccine.and(vaccineSpecList.get(i));
            }
        }
        return vaccineRepository.findAll(specVaccine,pageable);
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        Vaccine finalVaccine = vaccineList.get(vaccineList.size() - 1);
        String finalCode = finalVaccine.getCode();
        int finalNumber = Integer.parseInt( finalCode.substring( finalCode.length()-4 ) );
        String tempNumber = "";
        String nextCode;
        if (finalNumber < 9) tempNumber = "000";
        else if (finalNumber < 99) tempNumber = "00";
        else if (finalNumber < 999) tempNumber = "0";
        nextCode = "MVX-" + tempNumber + String.valueOf(finalNumber + 1);
        vaccine.setInventoryStatus("còn");
        vaccine.setCode(nextCode);
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine findById(Long id) {
        return vaccineRepository.findById(id).orElse(null);
    }
}
