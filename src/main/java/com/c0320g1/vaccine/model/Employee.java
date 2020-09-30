package com.c0320g1.vaccine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
Long id;

@Column(name = "code")
String code;

@Column(name = "full_name",nullable = false)
String fullName;

@Column(name = "birthday")
LocalDate birthday;

@Column(name = "id_card")
String idCard;

@Column(name = "position")
String position;

@Column(name = "phone_number")
String phoneNumber;

@Column(name = "address")
String address;

@Column(name = "role")
String role;

@Column(name = "is_delete")
Boolean isDelete;
}
