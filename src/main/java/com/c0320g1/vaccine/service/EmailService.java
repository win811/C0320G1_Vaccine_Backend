package com.c0320g1.vaccine.service;

import javax.mail.MessagingException;

public interface EmailService {
    //CREATE BY ANH DUC
    void sendSimpleMessage(String to, String subject, String text);

    //CREATE BY ANH DUC
    void sendSimpleHTMLMessage(String to, String subject, String text) throws MessagingException;
}
