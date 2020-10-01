package com.c0320g1.vaccine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "accounts")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 6)
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    @Size(min = 8)
    private String password;

    @Column(name = "full_name", nullable = false)
    @Size(min = 10)
    private String fullName;

    @Column(name = "id_card", nullable = false, unique = true)
    private String idCard;

    @Column(name = "address")
    private String address;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "avatar_image")
    private String avatar;

    @Column(name = "role")
    @Pattern(regexp = "^(ROLE_USER|ROLE_ADMIN)$")
    private String role ;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "confirm_status")
    private boolean confirmStatus = false;

}
