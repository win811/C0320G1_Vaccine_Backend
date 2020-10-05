package com.c0320g1.vaccine.service;
import com.c0320g1.vaccine.dto.PatientListDTO;
import com.c0320g1.vaccine.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface PatientService {
    //Thành Long
    Page<PatientListDTO> findPatientByCriteria(Specification<Patient> spec, int page);

    //Thành Long
    Specification<Patient> getFilter(String code, String fullName);

    //Thành Long
    Page<PatientListDTO> findAllPatient(int page);

    //Thành Long
    void update(Patient patient);

    // Thành Long
    void delete(Long id);

    // Thành Long
    Patient findById(Long id);
}
