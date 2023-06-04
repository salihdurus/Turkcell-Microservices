package com.turkcellGY.maintenanceservice.business.dto.requests.update;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceRequest {
    @NotNull
    private UUID carId;
    @Length(min = 5,max = 100)
    private String information;
    @NotNull
    private boolean isCompleted;
    @NotNull
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
