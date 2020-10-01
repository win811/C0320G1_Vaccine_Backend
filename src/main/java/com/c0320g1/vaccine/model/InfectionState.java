package com.c0320g1.vaccine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "infection_state")
@Getter
@Setter
@NoArgsConstructor
public class InfectionState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "survey_date")
    private LocalDate surveyDate;

    @Column(name = "infection_location")
    private String location;

    @Column(name = "infection_category")
    private String category;

    @Column(name = "infection_path")
    private String  path;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;
}
