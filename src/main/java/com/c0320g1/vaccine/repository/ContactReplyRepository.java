package com.c0320g1.vaccine.repository;

import com.c0320g1.vaccine.model.ContactReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactReplyRepository extends JpaRepository<ContactReply, Long> {
}
