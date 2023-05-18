package com.turkcellGY.inventoryservice.business.concretes;

import com.turkcellGY.inventoryservice.business.abstracts.CarService;
import com.turkcellGY.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcellGY.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcellGY.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
        return null;
    }

    @Override
    public GetCarResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        return null;
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
