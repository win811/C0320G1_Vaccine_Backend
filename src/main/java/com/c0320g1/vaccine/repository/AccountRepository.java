package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
