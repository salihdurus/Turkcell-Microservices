package com.turkcellGY.paymentservice.api.controllers;

import com.turkcellGY.paymentservice.business.abstracts.PaymentService;
import com.turkcellGY.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.turkcellGY.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.turkcellGY.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.turkcellGY.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.turkcellGY.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.turkcellGY.paymentservice.business.dto.responses.update.UpdatePaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable UUID id, @Valid @RequestBody UpdatePaymentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

//    @PostMapping("/check")
//    public ClientResponse proccessPayment(@RequestBody CreateRentalPaymentRequest request) {
//        return service.processPayment(request);
//    }
}
