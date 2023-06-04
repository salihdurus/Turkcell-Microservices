package com.turkcellGY.maintenanceservice.business.concretes;

import com.turkcellGY.commonpackage.events.maintenance.MaintenanceCompleteEvent;
import com.turkcellGY.commonpackage.events.maintenance.MaintenanceCreatedEvent;
import com.turkcellGY.commonpackage.utils.kafka.producer.KafkaProducer;
import com.turkcellGY.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellGY.maintenanceservice.api.clients.CarClient;
import com.turkcellGY.maintenanceservice.business.abstracts.MaintenanceService;
import com.turkcellGY.maintenanceservice.business.dto.requests.create.CreateMaintenanceRequest;
import com.turkcellGY.maintenanceservice.business.dto.requests.update.UpdateMaintenanceRequest;
import com.turkcellGY.maintenanceservice.business.dto.responses.create.CreateMaintenanceResponse;
import com.turkcellGY.maintenanceservice.business.dto.responses.get.GetAllMaintenancesResponse;
import com.turkcellGY.maintenanceservice.business.dto.responses.get.GetMaintenanceResponse;
import com.turkcellGY.maintenanceservice.business.dto.responses.update.UpdateMaintenanceResponse;
import com.turkcellGY.maintenanceservice.entities.Maintenance;
import com.turkcellGY.maintenanceservice.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapperService mapper;
    private final CarClient client;
    private final KafkaProducer producer;
    //TODO: kurallar eklenecek

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        return repository
                .findAll()
                .stream()
                .map(maintenance -> mapper
                        .forResponse()
                        .map(maintenance, GetAllMaintenancesResponse.class))
                .toList();
    }

    @Override
    public GetMaintenanceResponse getById(UUID id) {
        return mapper
                .forResponse()
                .map(repository.findById(id).orElseThrow(), GetMaintenanceResponse.class);
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(UUID carId) {
        var maintenance = repository.findByCarId(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        producer.sendMessage(new MaintenanceCompleteEvent(carId),"maintenance-completed");
        return mapper.forResponse().map(maintenance, GetMaintenanceResponse.class);

    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkIfCarAvailable(request.getCarId());
        var maintenance = mapper.forRequest().map(request, Maintenance.class);
        maintenance.setId(null);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        var createdMaintenance = repository.save(maintenance);
        producer.sendMessage(new MaintenanceCreatedEvent(request.getCarId()),"maintenance-created");
        return mapper.forResponse().map(createdMaintenance, CreateMaintenanceResponse.class);

    }

    @Override
    public UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest request) {
        var maintenance = mapper.forRequest().map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        return mapper.forResponse().map(maintenance, UpdateMaintenanceResponse.class);
    }

    @Override
    public void delete(UUID id) {
        var carId = repository.findById(id).orElseThrow().getCarId();
        repository.deleteById(id);
        producer.sendMessage(new MaintenanceCompleteEvent(carId),"maintenance-deleted");
    }

    private void checkIfCarAvailable(UUID carId) {
        var response = client.checkIfCarAvailable(carId);
        if (!response.isSuccess())
            throw new RuntimeException(response.getMessage());

    }
}
