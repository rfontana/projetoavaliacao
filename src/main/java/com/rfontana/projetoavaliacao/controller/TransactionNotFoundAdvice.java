package com.rfontana.projetoavaliacao.controller;

import com.rfontana.projetoavaliacao.exception.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TransactionNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String transactionNotFoundHandler(TransactionNotFoundException ex) {
        return ex.getMessage();
    }
}