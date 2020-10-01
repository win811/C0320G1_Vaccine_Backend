package com.c0320g1.vaccine.dto;

import com.c0320g1.vaccine.model.TransactionPatient;
import com.c0320g1.vaccine.model.Vaccine;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TransactionPatientDTO {

    private Long id;

    private LocalDate transactionDate;

    private Short amount;

    private PatientInfoDTO account;

    private Vaccine vaccine;

    public TransactionPatient map() {
        TransactionPatient trans = new TransactionPatient();
        trans.setId(id);
        trans.setTransactionDate(transactionDate);
        trans.setAmount(amount);
        trans.setVaccine(vaccine);
        trans.setAccount(account.map());
        return trans;
    }
}
