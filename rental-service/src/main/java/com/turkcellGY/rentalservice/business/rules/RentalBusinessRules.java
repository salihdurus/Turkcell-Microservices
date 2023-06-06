package com.turkcellGY.rentalservice.business.rules;


import com.turkcellGY.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcellGY.commonpackage.utils.exceptions.BusinessException;
import com.turkcellGY.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellGY.rentalservice.api.clients.CarClient;
import com.turkcellGY.rentalservice.api.clients.PaymentClient;
import com.turkcellGY.rentalservice.business.dto.requests.CreateRentalRequest;
import com.turkcellGY.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;
    private final CarClient carClient;
    private final PaymentClient paymentClient;
    private final ModelMapperService mapper;
    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("MODEL_NOT_EXISTS");
        }
    }

    public void ensureCarIsAvailable(UUID carId) {
        var response = carClient.checkIfCarAvailable(carId);
        if (!response.isSuccess()) {
            throw new BusinessException(response.getMessage());
        }
    }

    public void paymentIsSuccess(CreateRentalRequest request) {
        var paymentRequest = mapper.forRequest().map(request,CreateRentalPaymentRequest.class);
        paymentRequest.setPrice(request.getDailyPrice()*request.getRentedForDays());
        var response = paymentClient.processPayment(paymentRequest);
        if (!response.isSuccess()){
            throw new BusinessException(response.getMessage());
        }
    }
}
