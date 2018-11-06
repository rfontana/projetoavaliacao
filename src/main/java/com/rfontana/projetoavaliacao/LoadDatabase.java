package com.rfontana.projetoavaliacao;

import com.rfontana.projetoavaliacao.domain.Invoice;
import com.rfontana.projetoavaliacao.domain.Status;
import com.rfontana.projetoavaliacao.domain.Transaction;
import com.rfontana.projetoavaliacao.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
class LoadDatabase {

    Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    CommandLineRunner initDatabase(@Value("${loadtestdatabase}") boolean loadtestdatabase, InvoiceRepository invoiceRepository) {
        return args -> {
            if (loadtestdatabase) {

                log.info("Preloading database...");
                {

                    Invoice invoice;
                    Transaction transaction;
                    List<Transaction> transactions;

                    for (int i = 0; i < 1000; i++) {

                        //Transaction
                        transaction = new Transaction();
                        transaction.setCustomerId(i * 10L);
                        transaction.setMaskedCardNumber("************" + i + i + i + i);
                        transaction.setCurrencyCode("USD");
                        transaction.setDate(new Date());
                        transaction.setDeleted(Boolean.FALSE);
                        transaction.setStatus(Status.NEW.name());

                        //Transaction List
                        transactions = new ArrayList<>();
                        transactions.add(transaction);

                        //Invoice
                        invoice = new Invoice();
                        invoice.setTotal(new BigDecimal(900 + i));
                        invoice.setDate(new java.sql.Date(1541193445239L + i * 86400));
                        invoice.setStatus(Status.NEW.name());
                        invoice.setCurrencyCode("USD");
                        invoice.setDeleted(Boolean.FALSE);
                        invoice.setAmountPaid(new BigDecimal(500 + i));
                        invoice.setTax(0);
                        invoice.setTransactions(transactions);
                        log.debug("Preloading " + invoiceRepository.save(invoice));

                    }
                }
            }

        };

    }
}
