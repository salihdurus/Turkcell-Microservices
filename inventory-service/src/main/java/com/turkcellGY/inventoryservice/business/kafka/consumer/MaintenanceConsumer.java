package com.turkcellGY.inventoryservice.business.kafka.consumer;

import com.turkcellGY.commonpackage.events.maintenance.MaintenanceCompleteEvent;
import com.turkcellGY.commonpackage.events.maintenance.MaintenanceCreatedEvent;
import com.turkcellGY.inventoryservice.business.abstracts.CarService;
import com.turkcellGY.inventoryservice.entities.enums.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaintenanceConsumer {
    private final CarService service;
    @KafkaListener(
            topics = "maintenance-created",
            groupId = "inventory-maintenance-create"
    )
    public void consume(MaintenanceCreatedEvent event) {
        service.changeStateByCarId(State.Maintenance,event.getCarId());
        log.info("Maintenance created event consumed {}", event);
    }
    @KafkaListener(
            topics = {"maintenance-deleted", "maintenance-completed"},
            groupId = "inventory-maintenance-complete"
    )
    public void consume(MaintenanceCompleteEvent event) {
        service.changeStateByCarId(State.Available,event.getCarId());
        log.info("Maintenance complete event consumed {}", event);
    }
}
