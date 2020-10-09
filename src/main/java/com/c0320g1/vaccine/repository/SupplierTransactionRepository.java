package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.SupplierTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierTransactionRepository extends JpaRepository<SupplierTransaction, Long>, JpaSpecificationExecutor<SupplierTransaction> {
    Page<SupplierTransaction> findAllByIsDeleteIsFalse(Pageable pageable);
}
