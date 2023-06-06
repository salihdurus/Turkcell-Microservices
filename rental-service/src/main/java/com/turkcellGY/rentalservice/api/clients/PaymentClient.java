package com.turkcellGY.rentalservice.api.clients;

import com.turkcellGY.commonpackage.utils.dto.ClientResponse;
import com.turkcellGY.commonpackage.utils.dto.CreateRentalPaymentRequest;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service",fallback = PaymentClientFallback.class)
public interface PaymentClient {
    @Retry(name = "paymentStatus")
    @PostMapping(value = "/api/payments/check")
    ClientResponse processPayment(@RequestBody CreateRentalPaymentRequest request);
}
