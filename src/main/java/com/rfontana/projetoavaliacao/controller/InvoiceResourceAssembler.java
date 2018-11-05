package com.rfontana.projetoavaliacao.controller;


import com.rfontana.projetoavaliacao.domain.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
class InvoiceResourceAssembler implements ResourceAssembler<Invoice, Resource<Invoice>> {


    private final TransactionResourceAssembler transactionResourceAssembler;

    @Autowired
    InvoiceResourceAssembler(TransactionResourceAssembler transactionResourceAssembler) {
        this.transactionResourceAssembler = transactionResourceAssembler;
    }

    @Override
    public Resource<Invoice> toResource(Invoice invoice) {

        Resource<Invoice> invoiceResource = new Resource<>(invoice,
                linkTo(methodOn(InvoiceController.class).one(invoice.getInvoiceId())).withSelfRel(),
                linkTo(methodOn(InvoiceController.class).invoiceTransactions(invoice.getInvoiceId())).withSelfRel(),
                linkTo(methodOn(InvoiceController.class).all()).withRel("invoices")
        );

        return invoiceResource;
    }


}