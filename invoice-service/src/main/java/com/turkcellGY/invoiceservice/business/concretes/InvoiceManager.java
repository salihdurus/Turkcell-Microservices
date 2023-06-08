package com.turkcellGY.invoiceservice.business.concretes;


import com.turkcellGY.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellGY.invoiceservice.business.abstracts.InvoiceService;
import com.turkcellGY.invoiceservice.business.dto.requests.create.CreateInvoiceRequest;
import com.turkcellGY.invoiceservice.business.dto.requests.update.UpdateInvoiceRequest;
import com.turkcellGY.invoiceservice.business.dto.responses.create.CreateInvoiceResponse;
import com.turkcellGY.invoiceservice.business.dto.responses.get.GetAllInvoiceResponse;
import com.turkcellGY.invoiceservice.business.dto.responses.get.GetInvoiceResponse;
import com.turkcellGY.invoiceservice.business.dto.responses.update.UpdateInvoiceResponse;
import com.turkcellGY.invoiceservice.business.rules.InvoiceBusinessRules;
import com.turkcellGY.invoiceservice.entities.Invoice;
import com.turkcellGY.invoiceservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapperService mapper;
    private final InvoiceBusinessRules rules;

    @Override
    public List<GetAllInvoiceResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoiceResponse> response = invoices
                .stream()
                .map(invoice -> mapper.forResponse().map(invoice, GetAllInvoiceResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetInvoiceResponse getById(UUID id) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.forResponse().map(invoice,GetInvoiceResponse.class);
        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice=mapper.forResponse().map(request,Invoice.class);
        invoice.setId(UUID.randomUUID());
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        CreateInvoiceResponse response=mapper.forResponse().map(invoice,CreateInvoiceResponse.class);
        return response;
    }
    @Override
    public UpdateInvoiceResponse update(UUID id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice=mapper.forResponse().map(request,Invoice.class);
        invoice.setId(id);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        UpdateInvoiceResponse response=mapper.forResponse().map(invoice,UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfInvoiceExists(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Invoice invoice) {
        return invoice.getDailyPrice() * invoice.getRentedForDays();
    }


}
