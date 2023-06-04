package com.turkcellGY.commonpackage.events.maintenance;

import com.turkcellGY.commonpackage.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceCompleteEvent implements Event {
    private UUID carId;
}
