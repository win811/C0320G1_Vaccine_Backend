package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.Patient;
import com.c0320g1.vaccine.repository.PatientRepository;
import com.c0320g1.vaccine.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public void delete(Long id) {
        patientRepository.deleteById(id);
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


}
