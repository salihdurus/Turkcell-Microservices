package com.turkcellGY.inventoryservice.business.rules;

import com.turkcellGY.commonpackage.utils.exceptions.BusinessException;
import com.turkcellGY.commonpackage.utils.enums.State;
import com.turkcellGY.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CarBusinessRules {
    private final CarRepository repository;

    public void checkIfCarExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("CAR_NOT_EXISTS");
        }
    }
    public void checkIfCarAvailability(UUID id){
        var car=repository.findById(id).orElseThrow();
        if(!car.getState().equals(State.Available)){
            throw new BusinessException("CAR_NOT_AVAILABLE");
        }
    }
}
