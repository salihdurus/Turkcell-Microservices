package com.turkcellGY.inventoryservice.business.dto.requests.update;

import com.turkcellGY.commonpackage.utils.annotations.NotFutureYear;
import com.turkcellGY.commonpackage.utils.constans.Regex;
import com.turkcellGY.inventoryservice.entities.enums.State;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCarRequest {
    @NotNull
    @NotBlank
    private UUID modelId;
    @NotFutureYear
    @Min(value = 2000)
    private int modelYear;
    @NotBlank
    @NotNull
    @Pattern(regexp = Regex.Plate)
    private String plate;
    @NotBlank
    @NotNull
    private State state;
    @Min(value = 1)
    private double dailyPrice;
}

