package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.dto.VaccineListDTO;
import com.c0320g1.vaccine.model.Vaccine;
import com.c0320g1.vaccine.repository.VaccineRepository;
import com.c0320g1.vaccine.service.VaccineService;
import com.c0320g1.vaccine.service.search.SearchCriteria;
import com.c0320g1.vaccine.service.search.VaccineSpecification;
import com.c0320g1.vaccine.service.search.VaccineSpecification2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    /**
     * Creator : B-Cường
     */
    @Override
    public Page<Vaccine> findBySpec(String name, String category, String country, String inventoryStatus, int page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "expiryDate");
        Pageable pageable = PageRequest.of(page - 1, 4, sort);
        Specification<Vaccine> specVaccine = null;
        List<VaccineSpecification> vaccineSpecList = new ArrayList<>();
        vaccineSpecList.add(new VaccineSpecification("name", "like", name));
        vaccineSpecList.add(new VaccineSpecification("category", "like", category));
        vaccineSpecList.add(new VaccineSpecification("country", "like", country));
        vaccineSpecList.add(new VaccineSpecification("inventoryStatus", "like", inventoryStatus));
        if (!vaccineSpecList.isEmpty()) {
            specVaccine = Specification.where(vaccineSpecList.get(0));
            for (int i = 1; i < vaccineSpecList.size(); i++) {
                assert specVaccine != null;
                specVaccine = specVaccine.and(vaccineSpecList.get(i));
            }
        }
        return vaccineRepository.findAll(specVaccine, pageable);
    }

    /**
     * Creator : B-Cường
     */
    @Override
    public Vaccine save(Vaccine vaccine) {
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        Vaccine finalVaccine = vaccineList.get(vaccineList.size() - 1);
        String finalCode = finalVaccine.getCode();
        int finalNumber = Integer.parseInt(finalCode.substring(finalCode.length() - 4));
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

    /**
     * Creator : B-Cường
     */
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

    // Thành Long
    @Override
    public Page<VaccineListDTO> findVaccineByCriteria(Specification<Vaccine> spec, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Vaccine> vaccines = vaccineRepository.findAll(spec, pageable);
        return transferToNewPage(vaccines);
    }

    // Thành Long
    @Override
    public Specification<Vaccine> getFilter(String code, String category, String country, String minPrice, String maxPrice) {
        List<VaccineSpecification2> specs = new ArrayList<>();
        Specification<Vaccine> spec;
        // search theo code
        if (!"".equals(code) && !"undefined".equals(code)) {
            specs.add(new VaccineSpecification2(new SearchCriteria("code", "like", code)));
        }
        // search theo category
        if (!"".equals(category) && !"undefined".equals(category)) {
            specs.add(new VaccineSpecification2(new SearchCriteria("category", "like", category)));
        }
        // search theo country
        if (!"".equals(country) && !"undefined".equals(country)) {
            specs.add(new VaccineSpecification2(new SearchCriteria("country", "like", country)));
        }
        // search theo price
        if (!"undefined".equals(minPrice) && !"undefined".equals(maxPrice) && !"".equals(minPrice) && !"".equals(maxPrice)) {
            // trường hợp lớn hơn > x.xxx.xxx vnd
            if (maxPrice.equals("max")) {
                specs.add(new VaccineSpecification2(new SearchCriteria("price", "gt", minPrice)));
            } else {
                specs.add(new VaccineSpecification2(new SearchCriteria("price", "between", minPrice, maxPrice)));
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

    // Thành Long
    @Override
    public Page<VaccineListDTO> findAllVaccine(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Vaccine> vaccines = vaccineRepository.findAll(pageable);
        return transferToNewPage(vaccines);
    }

    // Thành Long
    @Override
    public void update(Vaccine vaccine) {
        vaccineRepository.save(vaccine);
    }

    // Thành Long
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

    // Thành Long
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
