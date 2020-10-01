package com.c0320g1.vaccine.service.search;

import com.c0320g1.vaccine.model.Vaccine;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class VaccineSpecification implements Specification<Vaccine> {

    private String key;
    private String operation;
    private String value;

    public VaccineSpecification(){
    }

    public VaccineSpecification(String key,String operation,String value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Vaccine> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (this.operation.equalsIgnoreCase("like")) {
            return criteriaBuilder.like(root.get(this.key),"%" + this.value + "%");
        }
        return null;
    }
}
