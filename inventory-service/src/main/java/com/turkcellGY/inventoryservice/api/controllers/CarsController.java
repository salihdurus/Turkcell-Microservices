package com.turkcellGY.inventoryservice.api.controllers;

import com.turkcellGY.commonpackage.utils.constans.Roles;
import com.turkcellGY.commonpackage.utils.dto.ClientResponse;
import com.turkcellGY.commonpackage.utils.dto.GetCarResponse;
import com.turkcellGY.inventoryservice.business.abstracts.CarService;
import com.turkcellGY.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcellGY.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcellGY.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {
    private final CarService service;
    @GetMapping
//    @Secured("ROLE_admin")
    @PreAuthorize(Roles.AdminOrModerator)
    public List<GetAllCarsResponse> getAll() {
        return service.getAll();
    }

    @PostAuthorize(Roles.AdminOrModerator +"|| returnObject.modelYear==2023")
    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable UUID id, @AuthenticationPrincipal Jwt jwt) {
//        System.out.println(jwt.getClaims().get("preferred_username"));
//        System.out.println(jwt.getClaims().get("email"));
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@Valid @RequestBody CreateCarRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCarRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping("/check-car-available/{id}")
    public ClientResponse checkIfCarAvailable(@PathVariable UUID id){
        return service.checkIfCarAvailable(id);
    }

}
