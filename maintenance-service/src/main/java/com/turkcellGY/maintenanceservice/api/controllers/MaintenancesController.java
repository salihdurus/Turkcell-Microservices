package com.turkcellGY.maintenanceservice.api.controllers;

import com.turkcellGY.maintenanceservice.business.abstracts.MaintenanceService;
import com.turkcellGY.maintenanceservice.business.dto.requests.create.CreateMaintenanceRequest;
import com.turkcellGY.maintenanceservice.business.dto.requests.update.UpdateMaintenanceRequest;
import com.turkcellGY.maintenanceservice.business.dto.responses.create.CreateMaintenanceResponse;
import com.turkcellGY.maintenanceservice.business.dto.responses.get.GetAllMaintenancesResponse;
import com.turkcellGY.maintenanceservice.business.dto.responses.get.GetMaintenanceResponse;
import com.turkcellGY.maintenanceservice.business.dto.responses.update.UpdateMaintenanceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenances")
public class MaintenancesController {
    private final MaintenanceService service;

    @GetMapping
    public List<GetAllMaintenancesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CreateMaintenanceResponse add(@Valid @RequestBody CreateMaintenanceRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@Valid @PathVariable UUID id, @RequestBody UpdateMaintenanceRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PutMapping("/return")
    public GetMaintenanceResponse returnCarFromMaintenance(@RequestParam UUID carId) {
        return service.returnCarFromMaintenance(carId);
    }

}
