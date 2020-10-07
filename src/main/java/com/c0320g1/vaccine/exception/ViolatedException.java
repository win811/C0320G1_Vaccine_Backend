package com.c0320g1.vaccine.exception;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Getter
@Setter
@NoArgsConstructor
public class ViolatedException extends Exception {

    private BindingResult bindingResult;

    public ViolatedException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }


}
