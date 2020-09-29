package com.c0320g1.vaccine.dto;

import com.c0320g1.vaccine.model.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//    Quân
public class InjectionHistoryDTO {
    private LocalDateTime injectionDate;
    private Patient patient;
    private String isInjected;
}
