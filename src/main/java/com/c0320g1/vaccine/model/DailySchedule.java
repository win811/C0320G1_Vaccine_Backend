package com.c0320g1.vaccine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "daily_schedule")
//An
public class DailySchedule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "start_code")
    private LocalTime start_Time;

    @Column(name = "end_code")
    private LocalTime end_Time;

    @Column(name = "address")
    private String address;

    @Column(name = "note")
    private String note;

    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;

//    1-nhiều
    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine ;

}
