package com.turkcellGY.inventoryservice.api.controllers;

import com.turkcellGY.inventoryservice.business.abstracts.CarService;
import com.turkcellGY.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {
    private final CarService service;
//    @GetMapping
//    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
//        return service.getAll();
//    }
}
