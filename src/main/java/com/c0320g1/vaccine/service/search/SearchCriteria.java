package com.c0320g1.vaccine.service.search;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Thành Long
@Getter
@Setter
public class SearchCriteria {
    private String key;
    private String operation;
    private List<String> values;

    public SearchCriteria() {
        this.values = new ArrayList<>();
    }
    public SearchCriteria(String key, String operation, String... values) {
        this.key = key;
        this.operation = operation;
        this.values = new ArrayList<>();
        this.values.addAll(Arrays.asList(values));
    }
}
