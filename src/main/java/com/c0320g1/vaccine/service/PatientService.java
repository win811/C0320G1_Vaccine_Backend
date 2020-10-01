package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.Employee;
import com.c0320g1.vaccine.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    //    CREATE BY ANH ĐỨC
    Page<Patient> findAll(Pageable pageable);

    //    CREATE BY ANH ĐỨC
    void save(Patient patient);

    //    CREATE BY ANH ĐỨC
    void delete(Long id);

    //    CREATE BY ANH ĐỨC
    Page<Patient> search(String key, String value, Pageable pageable);
}
