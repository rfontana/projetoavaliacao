package com.rfontana.projetoavaliacao.controller;


import com.rfontana.projetoavaliacao.domain.Transaction;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
class TransactionResourceAssembler implements ResourceAssembler<Transaction, Resource<Transaction>> {

    @Override
    public Resource<Transaction> toResource(Transaction transaction) {

        Resource<Transaction> transactionResource = new Resource<>(transaction,
                linkTo(methodOn(TransactionController.class).one(transaction.getTransactionId())).withSelfRel(),
                linkTo(methodOn(TransactionController.class).all()).withRel("transactions")
        );

        return transactionResource;
    }
}