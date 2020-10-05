package com.c0320g1.vaccine.security;

import com.c0320g1.vaccine.model.Account;
import com.c0320g1.vaccine.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsernameAndConfirmStatusIsTrue(s);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(account);
    }
}
