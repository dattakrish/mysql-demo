package com.kd.personal.mysql.controller;

import com.kd.personal.mysql.dto.RequestDto;
import com.kd.personal.mysql.entity.Person;
import com.kd.personal.mysql.entity.QPerson;
import com.kd.personal.mysql.entity.Transaction;
import com.kd.personal.mysql.repository.PersonRepository;
import com.kd.personal.mysql.repository.TransactionRepository;
import com.kd.personal.mysql.util.TransactionSpecification;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public Page<Person> persons(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "500") int size,
            @RequestParam(name = "firstname", required = false) String firstname,
            @RequestParam(name = "lastname", required = false) String lastname,
            @RequestParam(name = "budget", required = false) Integer budget,
            @RequestParam(name = "dobLimit", required = false) Long dobLimit) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Predicate<Person> predicate = null;
        QPerson person = QPerson.person;
        if (firstname != null && !firstname.isEmpty()) {
            booleanBuilder.or(person.firstname.eq(firstname));
        }

        if (lastname != null && !lastname.isEmpty()) {
            booleanBuilder.or(person.lastname.eq(lastname));
        }

        if (budget != null && budget != 0) {
            booleanBuilder.and(person.budget.goe(budget));
        }

        if (dobLimit != null && dobLimit != 0) {
            booleanBuilder.and(
                    person.dob.before(Instant.ofEpochSecond(dobLimit)));
        }
//    BooleanExpression expression =
        if (booleanBuilder.getValue() != null) {
            return personRepository.findAll(booleanBuilder.getValue(),
                    PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
        } else {
            return personRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<List<Transaction>> hello(@RequestBody RequestDto requestDto){
        //@RequestBody RequestDto requestDto
        TransactionSpecification transactionSpecification = new TransactionSpecification(requestDto);
        return new ResponseEntity(transactionRepository.findAll(transactionSpecification), HttpStatus.OK);
//        return transactionRepository.findAll();
    }
}