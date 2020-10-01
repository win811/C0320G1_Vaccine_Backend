package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.SupplierTransaction;
import com.c0320g1.vaccine.repository.SupplierTransactionRepository;
import com.c0320g1.vaccine.service.SupplierTransactionService;
import com.c0320g1.vaccine.service.search.SearchCriteria;
import com.c0320g1.vaccine.service.search.TransactionSpecification;
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
public class SupplierTransactionServiceImpl implements SupplierTransactionService {

    @Autowired
    private SupplierTransactionRepository supplierTransactionRepository;


    @Override
    public Page<SupplierTransaction> findAll(int page) {
        Pageable pageable = PageRequest.of(page -1,5, Sort.by("id"));
        Page<SupplierTransaction> supplierTransactions = supplierTransactionRepository.findAllByIsDeleteIsFalse(pageable);
        return supplierTransactions;
    }

    @Override
    public Specification<SupplierTransaction> getFilter(String tradingCode, String vaccineCode, String billCode, String vaccineType, String supplier) {
        List<TransactionSpecification> specs = new ArrayList<>();
        Specification<SupplierTransaction> spec;
        if(tradingCode != null && !"undefined".equals(tradingCode) && !"".equals(tradingCode)){
            specs.add(new TransactionSpecification(new SearchCriteria("tradingCode", "like", tradingCode)));
        }
        if(vaccineCode != null && !"undefined".equals(vaccineCode) && !"".equals(vaccineCode)){
            specs.add(new TransactionSpecification(new SearchCriteria("vaccineCode", "like", vaccineCode)));
        }
        if(billCode != null && !"undefined".equals(billCode) && !"".equals(billCode)){
            specs.add(new TransactionSpecification(new SearchCriteria("billCode", "like", billCode)));
        }
        if(vaccineType != null && !"undefined".equals(vaccineType) && !"".equals(vaccineType)){
            specs.add(new TransactionSpecification(new SearchCriteria("vaccineType", "like", vaccineType)));
        }
        if(supplier != null && !"undefined".equals(supplier) && !"".equals(supplier)){
            specs.add(new TransactionSpecification(new SearchCriteria("supplier", "like", supplier)));
        }
        if (specs.size() != 0){
            spec = Specification.where(specs.get(0));
            for(int i = 1; i<specs.size(); i++){
                assert spec != null;
                spec = spec.and(specs.get(i));
            }
            return spec;
        }
        return null;
    }

    @Override
    public Page<SupplierTransaction> findTransactionByCriteria(Specification<SupplierTransaction> spec, int page) {
        Pageable pageable =PageRequest.of(page - 1, 5, Sort.by("id"));
        Page<SupplierTransaction> supplierTransactions = supplierTransactionRepository.findAll(spec, pageable);
        return supplierTransactions;
    }


    @Override
    public SupplierTransaction findById(Long id) {
        return supplierTransactionRepository.findById(id).orElse(null);
    }

    @Override
    public void save(SupplierTransaction supplierTransaction) {
        supplierTransactionRepository.save(supplierTransaction);
    }

    @Override
    public void delete(SupplierTransaction supplierTransaction) {
        supplierTransaction.setIsDelete(Boolean.TRUE);
        supplierTransactionRepository.save(supplierTransaction);
    }
}
