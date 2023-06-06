package com.turkcellGY.paymentservice.adapters;

import com.turkcellGY.commonpackage.utils.exceptions.BusinessException;
import com.turkcellGY.paymentservice.business.abstracts.PosService;
import org.springframework.stereotype.Service;

@Service
public class FakePosServiceAdapter implements PosService {

    @Override
    public void pay() {
        boolean isPaymentSuccessful = true;//new Random().nextBoolean();
        if (!isPaymentSuccessful) {
            throw new BusinessException("PAYMENT_FAILED");
        }
    }
}
