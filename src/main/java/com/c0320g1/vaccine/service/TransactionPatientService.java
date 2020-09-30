package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.dto.TransactionPatientDTO;
import org.springframework.data.domain.Page;

public interface TransactionPatientService {
    Page<TransactionPatientDTO> getAllTransaction(Long vaccineId, String fullName, int page);
    void updateTransaction(TransactionPatientDTO editedTransaction);
    void delete(Long id);
}
