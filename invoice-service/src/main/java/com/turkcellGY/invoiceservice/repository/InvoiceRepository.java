package com.turkcellGY.invoiceservice.repository;

import com.turkcellGY.invoiceservice.entities.Invoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface InvoiceRepository extends ElasticsearchRepository<Invoice, UUID> {
    List<Invoice> findAll();
}
