package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.model.VerifyToken;
import com.c0320g1.vaccine.repository.VerifyTokenRepository;
import com.c0320g1.vaccine.service.VerifyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class VerifyTokenServiceImpl implements VerifyTokenService {
    @Autowired
    private VerifyTokenRepository verifyTokenRepository;

    @Override
    public void save(VerifyToken verifyToken) {
        verifyTokenRepository.save(verifyToken);
    }

    @Override
    public VerifyToken findByEmail(String email) {
        return verifyTokenRepository.findByEmail(email);
    }

    @Override
    public Boolean checkTokenVerify(String email, String code) {
        VerifyToken verifyToken1 = verifyTokenRepository.findByEmail(email);
        LocalDateTime timeNow = LocalDateTime.now();
       Duration timeEquals= Duration.between(verifyToken1.getTimeCreate(), timeNow);
        if (timeEquals.toMinutes()>5) {
            return false;
        }
        if (!verifyToken1.getCode().equals(code)) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteAllByEmail(String email) {
        verifyTokenRepository.removeAllByEmail(email);
    }
}
