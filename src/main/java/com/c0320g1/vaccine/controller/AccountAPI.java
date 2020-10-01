package com.c0320g1.vaccine.controller;
import com.c0320g1.vaccine.model.Account;
import com.c0320g1.vaccine.model.Contact;
import com.c0320g1.vaccine.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/accountAPI")
public class AccountAPI {
    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> saveAccount(
            @RequestBody Account account
    ) {
        Map<String, Object> response = new HashMap<>();
        account.setConfirmStatus(true);
        accountService.save(account);
        response.put("status", HttpStatus.OK);
        response.put("message", "Tạo mới account thành công !");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
