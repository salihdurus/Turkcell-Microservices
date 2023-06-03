package com.turkcellgy.filterservice.repository;

import com.turkcellgy.filterservice.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FilterRepository extends MongoRepository<Filter, UUID> {
    void deleteByCarId(UUID carId);
    void deleteAllByBrandId(UUID brandId);
    Filter findByCarId(UUID carId);
}
