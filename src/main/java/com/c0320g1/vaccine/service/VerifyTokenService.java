package com.c0320g1.vaccine.service;

import com.c0320g1.vaccine.model.VerifyToken;

public interface VerifyTokenService {
    void save(VerifyToken verifyToken);

    VerifyToken findByEmail(String email);

    Boolean checkTokenVerify(String email,String code);

    void deleteAllByEmail(String email);

}
