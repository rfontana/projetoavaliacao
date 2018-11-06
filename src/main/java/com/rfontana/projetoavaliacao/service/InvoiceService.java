package com.rfontana.projetoavaliacao.service;

import com.rfontana.projetoavaliacao.domain.Invoice;
import com.rfontana.projetoavaliacao.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService {

    Logger log = LoggerFactory.getLogger(InvoiceService.class);

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    public Page<Invoice> findAllPageable(Pageable pageable) {

        Page<Invoice> invoices = invoiceRepository.findAll(pageable);

        return invoices;
    }
}
