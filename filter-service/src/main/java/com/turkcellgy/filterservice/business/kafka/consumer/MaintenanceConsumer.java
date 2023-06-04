package com.turkcellgy.filterservice.business.kafka.consumer;

import com.turkcellGY.commonpackage.events.maintenance.MaintenanceCompleteEvent;
import com.turkcellGY.commonpackage.events.maintenance.MaintenanceCreatedEvent;
import com.turkcellgy.filterservice.business.abstracts.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaintenanceConsumer {
    private final FilterService service;

    @KafkaListener(
            topics = "maintenance-created",
            groupId = "filter-maintenance-create"
    )
    public void consume(MaintenanceCreatedEvent event) {
        var filter = service.getByCarId(event.getCarId());
        filter.setState("Maintenance");
        service.add(filter);
        log.info("Maintenance created event consumed {}", event);
    }

    @KafkaListener(
            topics = {"maintenance-deleted", "maintenance-completed"},
            groupId = "filter-maintenance-complete"
    )
    public void consume(MaintenanceCompleteEvent event) {
        var filter=service.getByCarId(event.getCarId());
        filter.setState("Available");
        service.add(filter);
        log.info("Maintenance complete event consumed {}", event);
    }
}
