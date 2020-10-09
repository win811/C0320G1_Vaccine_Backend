package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.Account;
import com.c0320g1.vaccine.repository.AccountRepository;
import com.c0320g1.vaccine.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findAccountByUserName(String username) {
        return accountRepository.findByUsername(username);
    }

}
