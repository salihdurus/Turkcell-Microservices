package com.turkcellGY.rentalservice.business.rules;


import com.turkcellGY.commonpackage.utils.exceptions.BusinessException;
import com.turkcellGY.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            // TODO: BusinessException
            throw new BusinessException("MODEL_NOT_EXISTS");
        }
    }
}
