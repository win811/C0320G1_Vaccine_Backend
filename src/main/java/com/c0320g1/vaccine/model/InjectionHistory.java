package com.c0320g1.vaccine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "injection_history")
@Getter
@Setter
@NoArgsConstructor
public class InjectionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_injected")
    private String isInjected;

    @Column(name = "injection_date")
    private LocalDateTime injectionDate;

    @Column(name = "response_content")
    private String reponseContent;

    @Column(name = "register_type")
    private String registerType;

    @ManyToOne
    @JoinColumn(name =  "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;
}
