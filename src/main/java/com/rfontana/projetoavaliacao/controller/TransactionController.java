package com.rfontana.projetoavaliacao.controller;


import com.rfontana.projetoavaliacao.ProjetoavaliacaoApplication;
import com.rfontana.projetoavaliacao.domain.Status;
import com.rfontana.projetoavaliacao.domain.Transaction;
import com.rfontana.projetoavaliacao.exception.TransactionNotFoundException;
import com.rfontana.projetoavaliacao.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
class TransactionController {

    Logger log = LoggerFactory.getLogger(TransactionController.class);


    private final TransactionRepository transactionRepository;
    private final TransactionResourceAssembler assembler;
    private final JmsTemplate jmsTemplate;

    @Autowired
    TransactionController(TransactionRepository transactionRepository,
                          TransactionResourceAssembler assembler,
                          JmsTemplate jmsTemplate) {

        this.transactionRepository = transactionRepository;
        this.assembler = assembler;
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/transactions")
    Resources<Resource<Transaction>> all() {

        List<Resource<Transaction>> transactions = transactionRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(transactions,
                linkTo(methodOn(TransactionController.class).all()).withSelfRel());
    }

    @GetMapping("/transactions/{id}")
    Resource<Transaction> one(@PathVariable Long id) {
        return assembler.toResource(
                transactionRepository.findById(id)
                        .orElseThrow(() -> new TransactionNotFoundException(id)));
    }

    @PostMapping("/transactions")
    ResponseEntity<Resource<Transaction>> newTransaction(@RequestBody Transaction transaction) {

        transaction.setStatus(Status.NEW.name());

        jmsTemplate.convertAndSend("transactions", transaction);

        log.debug("Message sent: " + transaction.toString());

        return ResponseEntity
                .created(linkTo(methodOn(TransactionController.class).one(transaction.getTransactionId())).toUri())
                .body(assembler.toResource(transaction));
    }


}