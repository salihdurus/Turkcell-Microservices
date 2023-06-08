package com.turkcellGY.invoiceservice.business.abstracts;

import com.turkcellGY.invoiceservice.business.dto.requests.create.CreateInvoiceRequest;
import com.turkcellGY.invoiceservice.business.dto.requests.update.UpdateInvoiceRequest;
import com.turkcellGY.invoiceservice.business.dto.responses.create.CreateInvoiceResponse;
import com.turkcellGY.invoiceservice.business.dto.responses.get.GetAllInvoiceResponse;
import com.turkcellGY.invoiceservice.business.dto.responses.get.GetInvoiceResponse;
import com.turkcellGY.invoiceservice.business.dto.responses.update.UpdateInvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    List<GetAllInvoiceResponse> getAll();
    GetInvoiceResponse getById(UUID id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(UUID id, UpdateInvoiceRequest request);
    void delete(UUID id);
}
