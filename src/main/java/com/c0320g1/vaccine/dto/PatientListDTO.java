package com.c0320g1.vaccine.dto;

import java.time.LocalDate;

public class PatientListDTO {
    private Long id;

    private String code;

    private String fullName;

    private LocalDate birthday;

    private String gender;

    private String parentName;

    private String phoneNumber;

    private String address;

    public PatientListDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getParentName() {
        return parentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
