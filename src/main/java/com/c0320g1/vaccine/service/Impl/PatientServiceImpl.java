package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.dto.PatientListDTO;
import com.c0320g1.vaccine.model.Patient;
import com.c0320g1.vaccine.repository.PatientRepository;
import com.c0320g1.vaccine.service.PatientService;
import com.c0320g1.vaccine.service.search.PatientSpecification;
import com.c0320g1.vaccine.service.search.SearchCriteria;
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
public class PatientServiceImpl implements PatientService {
    //    CREATE BY ANH ĐỨC
    @Autowired
    private PatientRepository patientRepository;

    //    CREATE BY ANH ĐỨC
    @Override
    public Page<Patient> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    //    CREATE BY ANH ĐỨC
    @Override
    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    //    CREATE BY ANH ĐỨC
    @Override
    public Page<Patient> search(String key, String value, Pageable pageable) {
        return null;
    }

    //    CREATE BY ANH ĐỨC
    @Override
    public Patient checkPatient(Patient patient) {
        Patient patient2 = new Patient();
        patient2 = this.patientRepository.findByBirthdayAndPhoneNumber(patient.getBirthday(), patient.getPhoneNumber());
        if (patient2 != null) {
            return patient2;
        }
        patient.setStatus(true);
        this.save(patient);
        patient = this.patientRepository.findByBirthdayAndPhoneNumber(patient.getBirthday(), patient.getPhoneNumber());
        return patient;

    }

    // Thành Long
    @Override
    public Page<PatientListDTO> findPatientByCriteria(Specification<Patient> spec, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Patient> patients = patientRepository.findAll(spec, pageable);
        return transferToNewPage(patients);
    }

    // Thành Long
    @Override
    public Specification<Patient> getFilter(String code, String fullName) {
        List<PatientSpecification> specs = new ArrayList<>();
        Specification<Patient> spec;
        // search theo code
        if(!"".equals(code) && !"undefined".equals(code)) {
            specs.add(new PatientSpecification(new SearchCriteria("code", "like", code)));
        }
        // search theo fullName
        if(!"".equals(fullName) && !"undefined".equals(fullName) ) {
            specs.add(new PatientSpecification(new SearchCriteria("fullName", "like", fullName)));
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

    //Thành Long
    @Override
    public Page<PatientListDTO> findAllPatient(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Patient> patients = patientRepository.findAllByStatusIsTrue(pageable);
        return transferToNewPage(patients);
    }

    // Thành Long
    @Override
    public void update(Patient patient) {
        patientRepository.save(patient);
    }

    // Thành Long
    @Override
    public void delete(Long id) {
        Patient patient = patientRepository.findAllByIdAndStatusIsTrue(id);
        patient.setStatus(false);
        patientRepository.save(patient);
    }

    // Thành Long
    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    // Thành Long
    private PatientListDTO transferToDTO(Patient temp) {
        PatientListDTO patient = new PatientListDTO();
        patient.setId(temp.getId());
        patient.setCode(temp.getCode());
        patient.setFullName(temp.getFullName());
        patient.setBirthday(temp.getBirthday());
        patient.setGender(temp.getGender());
        patient.setParentName(temp.getParentName());
        patient.setPhoneNumber(temp.getPhoneNumber());
        patient.setAddress(temp.getAddress());
        return patient;
    }

    // Thành Long
    private Page<PatientListDTO> transferToNewPage(Page<Patient> patients) {
        Patient temp;
        List<PatientListDTO> patientListDTOS = new ArrayList<>();
        Iterator iterator = patients.iterator();
        while (iterator.hasNext()) {
            temp = (Patient) iterator.next();
            patientListDTOS.add(transferToDTO(temp));
        }
        Page<PatientListDTO> _patient = new PageImpl<>(patientListDTOS, patients.getPageable(), patients.getTotalElements());
        return _patient;
    }


}
