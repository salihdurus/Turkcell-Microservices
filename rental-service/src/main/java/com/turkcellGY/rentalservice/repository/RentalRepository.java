package com.turkcellGY.rentalservice.repository;

import com.turkcellGY.rentalservice.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {
}
