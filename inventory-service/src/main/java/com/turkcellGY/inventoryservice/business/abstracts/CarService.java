package com.turkcellGY.inventoryservice.business.abstracts;

import com.turkcellGY.commonpackage.utils.dto.ClientResponse;
import com.turkcellGY.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcellGY.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcellGY.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcellGY.commonpackage.utils.dto.GetCarResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import com.turkcellGY.commonpackage.utils.enums.State;

import java.util.List;
import java.util.UUID;

public interface CarService {
    List<GetAllCarsResponse> getAll();

    GetCarResponse getById(UUID id);

    CreateCarResponse add(CreateCarRequest request);

    UpdateCarResponse update(UUID id, UpdateCarRequest request);

    void delete(UUID id);

    ClientResponse checkIfCarAvailable(UUID id);

    void changeStateByCarId(State state, UUID id);
}
