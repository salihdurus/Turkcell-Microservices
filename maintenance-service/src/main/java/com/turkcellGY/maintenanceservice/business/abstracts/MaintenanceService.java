package com.turkcellGY.maintenanceservice.business.abstracts;

import com.turkcellGY.maintenanceservice.business.dto.requests.create.CreateMaintenanceRequest;
import com.turkcellGY.maintenanceservice.business.dto.requests.update.UpdateMaintenanceRequest;
import com.turkcellGY.maintenanceservice.business.dto.responses.create.CreateMaintenanceResponse;
import com.turkcellGY.maintenanceservice.business.dto.responses.get.GetAllMaintenancesResponse;
import com.turkcellGY.maintenanceservice.business.dto.responses.get.GetMaintenanceResponse;
import com.turkcellGY.maintenanceservice.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;
import java.util.UUID;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(UUID id);
    GetMaintenanceResponse returnCarFromMaintenance(UUID carId);
    CreateMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest);
    UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest updateMaintenanceRequest);
    void delete(UUID id);
}
