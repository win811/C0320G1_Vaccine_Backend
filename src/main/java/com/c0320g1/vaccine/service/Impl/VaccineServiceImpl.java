package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.dto.VaccineListDTO;
import com.c0320g1.vaccine.model.Vaccine;
import com.c0320g1.vaccine.repository.VaccineRepository;
import com.c0320g1.vaccine.service.VaccineService;
import com.c0320g1.vaccine.service.search.SearchCriteria;
import com.c0320g1.vaccine.service.search.VaccineSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public Page<VaccineListDTO> findVaccineByCriteria(Specification<Vaccine> spec, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Vaccine> vaccines = vaccineRepository.findAll(spec, pageable);
        return transferToNewPage(vaccines);
    }

    @Override
    public Specification<Vaccine> getFilter(String code, String category, String country, String minPrice, String maxPrice) {
        List<VaccineSpecification> specs = new ArrayList<>();
        Specification<Vaccine> spec;
        // search theo code
        if(!"".equals(code) && !"undefined".equals(code)) {
            specs.add(new VaccineSpecification(new SearchCriteria("code", "like", code)));
        }
        // search theo category
        if(!"".equals(category) && !"undefined".equals(category) ) {
            specs.add(new VaccineSpecification(new SearchCriteria("category", "like", category)));
        }
        // search theo country
        if(!"".equals(country) && !"undefined".equals(country) ) {
            specs.add(new VaccineSpecification(new SearchCriteria("country", "like", country)));
        }
        // search theo price
        if(!"undefined".equals(minPrice) && !"undefined".equals(maxPrice) && !"".equals(minPrice) && !"".equals(maxPrice)) {
            // trường hợp lớn hơn > x.xxx.xxx vnd
            if(maxPrice.equals("max")) {
                specs.add(new VaccineSpecification(new SearchCriteria("price", "gt", minPrice)));
            } else {
                specs.add(new VaccineSpecification(new SearchCriteria("price", "between", minPrice, maxPrice)));
            }
        }
        if (specs.size() != 0) {
            spec = Specification.where(specs.get(0));
            for (int i = 1; i < specs.size(); i++) {
                assert spec != null;
                spec = spec.and(specs.get(i));
            }
            return spec;
        }
        return null;
    }

    @Override
    public Page<VaccineListDTO> findAllVaccine(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Vaccine> vaccines = vaccineRepository.findAll(pageable);
        return transferToNewPage(vaccines);
    }

    @Override
    public void update(Vaccine vaccine) {
        vaccineRepository.save(vaccine);
    }

    private VaccineListDTO transferToDTO(Vaccine temp) {
        VaccineListDTO vaccine = new VaccineListDTO();
        vaccine.setId(temp.getId());
        vaccine.setCode(temp.getCode());
        vaccine.setName(temp.getName());
        vaccine.setCategory(temp.getCategory());
        vaccine.setCountry(temp.getCountry());
        vaccine.setLimitAge(temp.getLimitAge());
        vaccine.setPrice(temp.getPrice());
        return vaccine;
    }

    private Page<VaccineListDTO> transferToNewPage(Page<Vaccine> vaccines) {
        Vaccine temp;
        List<VaccineListDTO> vaccineListDTOS = new ArrayList<>();
        Iterator iterator = vaccines.iterator();
        while (iterator.hasNext()) {
            temp = (Vaccine) iterator.next();
            vaccineListDTOS.add(transferToDTO(temp));
        }
        Page<VaccineListDTO> _vaccine = new PageImpl<>(vaccineListDTOS, vaccines.getPageable(), vaccines.getTotalElements());
        return _vaccine;
    }

}
