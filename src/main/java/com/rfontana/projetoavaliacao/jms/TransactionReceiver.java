package com.rfontana.projetoavaliacao.jms;

import com.rfontana.projetoavaliacao.domain.Transaction;
import com.rfontana.projetoavaliacao.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class TransactionReceiver {

    Logger log = LoggerFactory.getLogger(TransactionReceiver.class);


    private final TransactionRepository transactionRepository;


    @Autowired
    public TransactionReceiver(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @JmsListener(destination = "transactions", containerFactory = "myFactory")
    public void receiveMessage(Transaction transaction) {

        log.debug("Transaction received: " + transaction.toString());
        Transaction transactionSaved = transactionRepository.save(transaction);
        log.debug("Transaction saved!!!: " + transactionSaved.toString());
    }

}