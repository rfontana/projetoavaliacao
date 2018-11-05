package com.rfontana.projetoavaliacao.jms;

import com.rfontana.projetoavaliacao.domain.Invoice;
import com.rfontana.projetoavaliacao.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class InvoiceReceiver {

    Logger log = LoggerFactory.getLogger(InvoiceReceiver.class);


    private final InvoiceRepository invoiceRepository;


    @Autowired
    public InvoiceReceiver(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @JmsListener(destination = "queue.invoices", containerFactory = "myFactory")
    public void receiveMessage(Invoice invoice) {

        log.debug("Invoice received: " + invoice.toString());
        Invoice invoiceSaved = invoiceRepository.save(invoice);
        log.debug("Invoice saved!!!: " + invoiceSaved.toString());
    }

}