package com.rfontana.projetoavaliacao.controller;

import com.rfontana.projetoavaliacao.domain.Invoice;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
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
        ResponseEntity<String> invoices = restTemplate.getForEntity("http://localhost:8080/invoices/", String.class);
        logger.info("getForObject: \n" + invoices);
    }

    @org.junit.jupiter.api.Test
    void one() {

        ResponseEntity<String> r = restTemplate.getForEntity("http://localhost:8080/invoices/{id}", String.class, 111);
        logger.info("getForEntity: \n" + r.toString());

        Invoice s = restTemplate.getForObject("http://localhost:8080/invoices/{id}", Invoice.class, 111);
        logger.info("getForObject: \n" + s.toString());

    }

    @org.junit.jupiter.api.Test
    void newInvoice() {

        String body = "{\n" +
                "  \"amountPaid\": 750,\n" +
                "  \"currencyCode\": \"BRL\",\n" +
                "  \"date\": \"2018-11-05T20:07:25.536Z\",\n" +
                "  \"tax\": 0,\n" +
                "  \"total\": 750,\n" +
                "  \"transactions\": [\n" +
                "    {\n" +
                "      \"amount\": 750,\n" +
                "      \"currencyCode\": \"BRL\",\n" +
                "      \"date\": \"2018-11-05T20:07:25.536Z\",\n" +
                "      \"maskedCardNumber\": \"************0011\",\n" +
                "      \"paymentMethod\": \"CARD\"\n" +
                "\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + "xxxxxxxxxxxx");
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        ResponseEntity<String> response = restTemplate
                .exchange("http://localhost:8080/invoices/", HttpMethod.POST, entity, String.class);

        logger.info(response.toString());


    }

}