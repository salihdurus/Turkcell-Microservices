package com.turkcellGY.maintenanceservice.repository;

import com.turkcellGY.maintenanceservice.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {
    Maintenance findByCarId(UUID carId);
}
