package com.turkcellgy.filterservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllFiltersResponse {
    private UUID id;
    private UUID brandId;
    private UUID modelId;
    private UUID carId;
    private String modelName;
    private String brandName;
    private String plate;
    private String modelYear;
    private double dailyPrice;
    private String state;
}
