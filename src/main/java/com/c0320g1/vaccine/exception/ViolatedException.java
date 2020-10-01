package com.c0320g1.vaccine.exception;

import org.springframework.validation.BindingResult;

public class ViolatedException extends Exception{
    private BindingResult bindingResult;

    public ViolatedException(BindingResult bindingResult){
        this.bindingResult = bindingResult;
    }
}
