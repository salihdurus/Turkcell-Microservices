package com.turkcellGY.invoiceservice.business.kafka.consumer;

import com.turkcellGY.commonpackage.events.rental.RentalCreatedEvent;
import com.turkcellGY.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellGY.invoiceservice.business.abstracts.InvoiceService;
import com.turkcellGY.invoiceservice.business.dto.requests.create.CreateInvoiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final InvoiceService service;
    private final ModelMapperService mapper;
    @KafkaListener(
            topics = "invoice-created",
            groupId = "invoice-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        log.info("Rental created event consumed {}", event);
        var request = mapper.forRequest().map(event, CreateInvoiceRequest.class);
        service.add(request);
    }
}
