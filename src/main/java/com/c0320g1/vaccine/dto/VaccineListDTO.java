package com.c0320g1.vaccine.dto;

public class VaccineListDTO {
    private Long id;

    private String code;

    private String name;

    private String category;

    private String country;

    private Double limitAge;

    private Double price;

    public VaccineListDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCountry() {
        return country;
    }

    public Double getLimitAge() {
        return limitAge;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLimitAge(Double limitAge) {
        this.limitAge = limitAge;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
