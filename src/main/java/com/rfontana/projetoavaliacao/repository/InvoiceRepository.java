package com.rfontana.projetoavaliacao.repository;

import com.rfontana.projetoavaliacao.domain.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

    List<Invoice> findAll();

}
