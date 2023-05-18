package com.turkcellGY.inventoryservice.business.dto.requests.create;

import com.turkcellGY.inventoryservice.entities.enums.State;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarRequest {
    @NotNull
    @NotBlank
    private UUID modelId;
    //TODO: NotFuture custom annotation
    @Min(value = 2000)
    private int modelYear;
    @NotBlank
    @NotNull
    //TODO: Add Regex
    private String plate;
    @Min(value = 1)
    private double dailyPrice;
}