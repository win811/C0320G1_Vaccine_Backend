package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.dto.PatientInfoDTO;
import com.c0320g1.vaccine.dto.TransactionPatientDTO;
import com.c0320g1.vaccine.model.Account;
import com.c0320g1.vaccine.model.TransactionPatient;
import com.c0320g1.vaccine.model.Vaccine;
import com.c0320g1.vaccine.repository.TransactionPatientRepository;
import com.c0320g1.vaccine.service.TransactionPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Creator: Duy
 */

@Service
public class TransactionPatientServiceImpl implements TransactionPatientService {

    @Autowired
    private TransactionPatientRepository transactionPatientRepository;

    @Override
    public Page<TransactionPatientDTO> getAllTransaction(Long vaccineId, String fullName, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<TransactionPatient> trans = transactionPatientRepository.findAll(Objects.requireNonNull(Specification.where(hasVaccine(vaccineId))).and(hasPatient(fullName)), pageable);
        return new PageImpl<TransactionPatientDTO>(convert(trans.getContent()), trans.getPageable(), trans.getTotalElements());
    }

    @Override
    public void updateTransaction(TransactionPatientDTO editedTransaction) {
        transactionPatientRepository.save(editedTransaction.map());
    }

    @Override
    public void delete(Long id) throws IllegalArgumentException {
        transactionPatientRepository.deleteById(id);
    }

    private Specification<TransactionPatient> hasVaccine(Long id) {
        return new Specification<TransactionPatient>() {
            @Override
            public Predicate toPredicate(Root<TransactionPatient> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if(id > 0) {
//                    Join<TransactionPatient, Vaccine> joinRoot = root.join("")
                    return criteriaBuilder.equal(root.join("vaccine").get("id"), id);
                }
                return null;
            }
        };
    }

    private Specification<TransactionPatient> hasPatient(String fullName) {
        return new Specification<TransactionPatient>() {
            @Override
            public Predicate toPredicate(Root<TransactionPatient> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if(!"".equals(fullName)) {
                    return criteriaBuilder.like(root.join("account").get("fullName"), "%" + fullName + "%");
                }
                return null;
            }
        };
    }

    private List<TransactionPatientDTO> convert(List<TransactionPatient> content) {
        List<TransactionPatientDTO> transactionList = new ArrayList<>();
        for(TransactionPatient trans: content) {
            transactionList.add(map(trans));
        }
        return transactionList;
    }

    private TransactionPatientDTO map(TransactionPatient trans) {
        return TransactionPatientDTO.builder()
                .id(trans.getId())
                .amount(trans.getAmount())
                .transactionDate(trans.getTransactionDate())
                .vaccine(trans.getVaccine())
                .account(map(trans.getAccount()))
                .build();
    }

    private PatientInfoDTO map(Account account) {
        return PatientInfoDTO.builder()
                .id(account.getId())
                .fullName(account.getFullName())
                .build();
    }
}
