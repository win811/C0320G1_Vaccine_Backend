package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.repository.ContactReplyRepository;
import com.c0320g1.vaccine.service.ContactReplyService;
import com.c0320g1.vaccine.model.ContactReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactReplyImpl implements ContactReplyService {
    @Autowired
    private ContactReplyRepository contactReplyRepository;

    @Override
    public void save(ContactReply contactReply) {
        contactReplyRepository.save(contactReply);
    }
}
