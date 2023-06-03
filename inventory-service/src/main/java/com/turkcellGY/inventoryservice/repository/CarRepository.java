package com.turkcellGY.inventoryservice.repository;

import com.turkcellGY.inventoryservice.entities.Car;
import com.turkcellGY.inventoryservice.entities.enums.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    @Modifying
    @Transactional
    @Query(value = "update Car set state = :state where id = :id") //SPeL
    void changeStateByCarId(State state,UUID id);
}

