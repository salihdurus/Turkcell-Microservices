package com.turkcellGY.inventoryservice.business.kafka.consumer;

import com.turkcellGY.commonpackage.events.rental.RentalCreatedEvent;
import com.turkcellGY.inventoryservice.business.abstracts.CarService;
import com.turkcellGY.inventoryservice.entities.enums.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final CarService service;
    @KafkaListener(
            topics = "rental-created",
            groupId = "inventory-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        service.changeStateByCarId(State.Rented,event.getCarId());
        log.info("Rental created event consumed {}", event);
    }
}
