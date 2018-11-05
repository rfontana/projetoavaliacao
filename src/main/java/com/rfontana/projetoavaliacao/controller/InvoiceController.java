package com.rfontana.projetoavaliacao.controller;


import com.rfontana.projetoavaliacao.domain.Invoice;
import com.rfontana.projetoavaliacao.domain.Status;
import com.rfontana.projetoavaliacao.domain.Transaction;
import com.rfontana.projetoavaliacao.exception.InvoiceNotFoundException;
import com.rfontana.projetoavaliacao.repository.InvoiceRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
class InvoiceController {

    Logger log = LoggerFactory.getLogger(InvoiceController.class);

    private final InvoiceRepository invoiceRepository;
    private final TransactionResourceAssembler transactionResourceAssembler;
    private final TransactionRepository transactionRepository;
    private final InvoiceResourceAssembler assembler;
    private final JmsTemplate jmsTemplate;

    @Autowired
    InvoiceController(InvoiceRepository invoiceRepository,
                      TransactionRepository transactionRepository,
                      InvoiceResourceAssembler assembler,
                      TransactionResourceAssembler transactionResourceAssembler,
                      JmsTemplate jmsTemplate) {

        this.invoiceRepository = invoiceRepository;
        this.transactionRepository = transactionRepository;
        this.assembler = assembler;
        this.transactionResourceAssembler = transactionResourceAssembler;
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/invoices")
    Resources<Resource<Invoice>> all() {

        List<Resource<Invoice>> invoices = invoiceRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(invoices,
                linkTo(methodOn(InvoiceController.class).all()).withSelfRel());
    }

    @GetMapping("/invoices/{id}")
    Resource<Invoice> one(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return assembler.toResource(invoice.orElseThrow(() -> new InvoiceNotFoundException(id)));
    }


    @PostMapping("/invoices")
    ResponseEntity<Resource<Invoice>> newInvoice(@RequestBody Invoice invoice) {

        invoice.setStatus(Status.NEW.name());

        jmsTemplate.convertAndSend("invoices", invoice);

        log.debug("Message sent: " + invoice.toString());

        return ResponseEntity
                .created(linkTo(methodOn(InvoiceController.class).one(invoice.getInvoiceId())).toUri())
                .body(assembler.toResource(invoice));
    }

    @GetMapping("/invoices/{id}/transactions")
    Resources<Resource<Transaction>> invoiceTransactions(@PathVariable Long id) {

        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException(id));

        List<Resource<Transaction>> transactions = invoice.getTransactions().stream()
                .map(transactionResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(transactions,
                linkTo(methodOn(InvoiceController.class).invoiceTransactions(id)).withSelfRel());
    }

}