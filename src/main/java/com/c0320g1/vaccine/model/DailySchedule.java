package com.c0320g1.vaccine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "daily_schedule")
public class DailySchedule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "calendar_code")
    private String calendarCode;

    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;

//    1-nhiều
    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine ;

}
