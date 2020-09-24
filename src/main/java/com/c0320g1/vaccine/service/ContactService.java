package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {
    void save(Contact contact);

    Contact findById(Long id);

    Page<Contact> findAll(Pageable pageable);
}
