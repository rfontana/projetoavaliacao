package com.rfontana.projetoavaliacao.repository;

import com.rfontana.projetoavaliacao.domain.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    List<Transaction> findAll();

}
