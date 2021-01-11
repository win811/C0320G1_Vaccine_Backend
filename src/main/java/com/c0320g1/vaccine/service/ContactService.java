package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {
    //    CREATE BY ANH ĐỨC
    void save(Contact contact);
    //    CREATE BY ANH ĐỨC
    Contact findById(Long id);
    //    CREATE BY ANH ĐỨC
    Page<Contact> findAll(Pageable pageable);
}
