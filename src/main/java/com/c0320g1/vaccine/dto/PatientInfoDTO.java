package com.c0320g1.vaccine.dto;

import com.c0320g1.vaccine.model.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PatientInfoDTO {

    private Long id;
    private String fullName;

    public Account map() {
        Account account = new Account();
        account.setId(id);
        return account;
    }
}
