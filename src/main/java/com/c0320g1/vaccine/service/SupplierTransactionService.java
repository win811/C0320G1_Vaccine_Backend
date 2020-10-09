package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.SupplierTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierTransactionService {
    Page<SupplierTransaction> findAll(int page);
    Specification<SupplierTransaction> getFilter(String tradingCode, String vaccineCode, String billCode, String vaccineType, String supplier);
    Page<SupplierTransaction> findTransactionByCriteria(Specification<SupplierTransaction> spec, int page);
    SupplierTransaction findById(Long id);
    void save(SupplierTransaction supplierTransaction);
    void delete(SupplierTransaction supplierTransaction);
}
