package com.turkcellGY.paymentservice.business.abstracts;

import com.turkcellGY.commonpackage.utils.dto.ClientResponse;
import com.turkcellGY.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcellGY.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.turkcellGY.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.turkcellGY.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.turkcellGY.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.turkcellGY.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.turkcellGY.paymentservice.business.dto.responses.update.UpdatePaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(UUID id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request);
    void delete(UUID id);
    ClientResponse processPayment(CreateRentalPaymentRequest request);
}
