package com.c0320g1.vaccine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Getter
@Setter
@NoArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private Long id;

    @Column(name = "vaccine_name")
    private String name;

    @Column(name = "vaccine_code")
    private String code;

    @Column(name = "vaccine_category")
    private String category;

    @Column(name = "vaccine_receive_date")
    private LocalDate receiveDate;

    @Column(name = "vaccine_license_code")
    private String licenseCode;

    @Column(name = "vaccine_country")
    private String country;

    @Column(name = "vaccine_content")
    private Double content;

    @Column(name = "vaccine_amount")
    private Integer amount;

    @Column(name = "vaccine_expiry_date")
    private LocalDate expiryDate;

    @Column(name = "vaccine_conditions")
    private String conditions;

    @Column(name = "vaccine_limit_age")
    private Double limitAge;

    @Column(name = "vaccine_inventory_status")
    private String inventoryStatus;

    @Column(name = "vaccine_price")
    private Double price;


}
