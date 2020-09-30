package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    // Creator: Đức
    @Autowired
    private JavaMailSender javaMailSender;

    //CREATE BY ANH DUC
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("Trung Tâm Tiêm Chủng GPS <CGBVN@gmail.com>");
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }
}
