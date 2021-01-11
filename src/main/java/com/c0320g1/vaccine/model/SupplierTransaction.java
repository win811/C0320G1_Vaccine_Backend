package com.c0320g1.vaccine.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class SupplierTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "trading_code")
    private String tradingCode;

    @Column(name = "vaccine_code")
    private String vaccineCode;

    @Column(name = "vaccine_type")
    private String vaccineType;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "bill_code")
    private String billCode;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "trading_date")
    private LocalDate tradingDate;

    @Column(name = "price")
    private Long price;

    @Column(name = "total")
    private Long total;

    @Column(name = "is_delete")
    private Boolean isDelete;
}
