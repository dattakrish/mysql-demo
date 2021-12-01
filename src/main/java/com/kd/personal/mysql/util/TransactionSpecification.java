package com.kd.personal.mysql.util;

import com.kd.personal.mysql.dto.RequestDto;
import com.kd.personal.mysql.entity.Transaction;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification implements Specification<Transaction> {
    private RequestDto requestDto;

    public TransactionSpecification(RequestDto requestDto){
        this.requestDto = requestDto;
    }
    @SneakyThrows
    @Override
    public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Date from = new SimpleDateFormat("yyyy-MM-dd").parse(requestDto.getOpeningDateFrom());
        Date to = new SimpleDateFormat("yyyy-MM-dd").parse(requestDto.getOpeningDateTo());
        predicates.add(criteriaBuilder.between(root.<Date>get("closingDate").as(Date.class),  from, to));
//        predicates.add(criteriaBuilder.)
//        predicates.add(criteriaBuilder.lessThan(root.<Date>get("openingDate"),to));
//        predicates.add(criteriaBuilder.greaterThan(root.<Date>get("openingDate"),from));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
