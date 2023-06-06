package com.turkcellGY.paymentservice.business.concretes;

import com.turkcellGY.commonpackage.utils.dto.ClientResponse;
import com.turkcellGY.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcellGY.commonpackage.utils.exceptions.BusinessException;
import com.turkcellGY.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellGY.paymentservice.business.abstracts.PaymentService;
import com.turkcellGY.paymentservice.business.abstracts.PosService;
import com.turkcellGY.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.turkcellGY.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.turkcellGY.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.turkcellGY.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.turkcellGY.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.turkcellGY.paymentservice.business.dto.responses.update.UpdatePaymentResponse;
import com.turkcellGY.paymentservice.business.rules.PaymentBusinessRules;
import com.turkcellGY.paymentservice.entities.Payment;
import com.turkcellGY.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapperService mapper;
    private final PaymentBusinessRules rules;
    private final PosService posService;
    @Override
    public List<GetAllPaymentsResponse> getAll() {
        var payments = repository.findAll();
        return payments.stream().map(payment -> mapper.forResponse().map(payment, GetAllPaymentsResponse.class)).toList();
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        rules.checkIfPaymentExists(id);
        var payment = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(payment, GetPaymentResponse.class);
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardNumberExists(request.getCardNumber());
        Payment payment =
                mapper.forRequest().map(request, Payment.class);
        payment.setId(UUID.randomUUID());
        repository.save(payment);
        return mapper.forResponse().map(payment, CreatePaymentResponse.class);
    }

    @Override
    public UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        var payment =
                mapper.forRequest().map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        return mapper.forResponse().map(payment, UpdatePaymentResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public ClientResponse processPayment(CreateRentalPaymentRequest request) {
        var response = new ClientResponse();
        processPaymentTransaction(request, response);

        return response;
    }
    private void processPaymentTransaction(CreateRentalPaymentRequest request, ClientResponse response) {
        try {
            rules.checkIfPaymentValid(request);
            var payment = repository.findByCardNumber(request.getCardNumber());
            double balance = payment.getBalance();
            rules.checkIfBalanceIsEnough(balance, request.getPrice());
            posService.pay();
            payment.setBalance(balance - request.getPrice());
            repository.save(payment);
            response.setSuccess(true);
        } catch (BusinessException exception) {
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }
    }
}
