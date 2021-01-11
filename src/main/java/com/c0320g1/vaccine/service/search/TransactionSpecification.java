package com.c0320g1.vaccine.service.search;

import com.c0320g1.vaccine.model.SupplierTransaction;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TransactionSpecification implements Specification<SupplierTransaction> {

    private final SearchCriteria criteria;

    public TransactionSpecification(SearchCriteria criteria){
        this.criteria = criteria;
    }
    @Override
    public Predicate toPredicate(Root<SupplierTransaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if(criteria.getOperation().equalsIgnoreCase("like")){
            return criteriaBuilder.like(root.get(criteria.getKey()),"%" + criteria.getValues().get(0) + "%");
        }else if (criteria.getOperation().equalsIgnoreCase("equal")){
            Long id = Long.valueOf(criteria.getValues().get(0));
            return criteriaBuilder.equal(root.get(criteria.getKey()), id);
        }else {
            return null;
        }
    }
}
