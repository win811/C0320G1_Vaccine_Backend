package com.c0320g1.vaccine.service.search;

import com.c0320g1.vaccine.model.Vaccine;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

//Long
public class VaccineSpecification2 implements Specification<Vaccine> {

    private final SearchCriteria criteria;

    public VaccineSpecification2(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Vaccine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase("like")) {
            // value like %chuỗi_tìm_kiếm%
            return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValues().get(0) + "%");
        } else if (criteria.getOperation().equalsIgnoreCase("between")) {
            // value between moneyFrom and moneyTo
            Double moneyFrom = Double.valueOf(criteria.getValues().get(0));
            Double moneyTo = Double.valueOf(criteria.getValues().get(1));
            return criteriaBuilder.between(root.get(criteria.getKey()), moneyFrom, moneyTo);
        } else {
            return null;
        }
    }
}
