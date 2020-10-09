package com.c0320g1.vaccine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String code;
    private String fullName;
    private String gender;
    private LocalDate birthday;
    private String parentName;
    private String address;
    private String phoneNumber;
    private String email;
    private Boolean status;

//    public void setCode(Long code) {
//        this.code = "BN-"+1000+ code;
//    }
}
