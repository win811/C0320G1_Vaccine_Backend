package com.c0320g1.vaccine.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "daily_schedule")
public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "calendar_code")
    private String calendarCode;

    @Column(name = "vaccination_date")
    private LocalDate vaccination_date;

    @Column(name = "address")
    private String address;

//    @ManyToOne
//    @JoinColumn(name = "vaccine_id")
//    private Vaccine vaccine;
}
