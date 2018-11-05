package com.rfontana.projetoavaliacao.controller;

import com.rfontana.projetoavaliacao.domain.Invoice;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class InvoiceControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceControllerTest.class);

    @Autowired
    TestRestTemplate restTemplate;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.Test
    void all() {
    }

    @org.junit.jupiter.api.Test
    void one() {

        ResponseEntity<Invoice> r = restTemplate.getForEntity("http://localhost:8080/invoices/{id}", Invoice.class, 111);
        logger.info("getForEntity: \n" + r.toString());

        Invoice s = restTemplate.getForObject("http://localhost:8080/invoices/{id}", Invoice.class, 111);
        logger.info("getForObject: \n" + s.toString());

    }

    @org.junit.jupiter.api.Test
    void newInvoice() {
    }

    @org.junit.jupiter.api.Test
    void newInvoiceAsync() {
    }
}