package com.turkcellGY.rentalservice.business.dto.requests;

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
public class CreateRentalRequest {
    @NotNull
    private UUID carId;
    @Min(1)
    private double dailyPrice;
    @Min(1)
    private int rentedForDays;

    @NotEmpty(message = "Card number is required")
    @Size(min = 16,max = 16,message = "Car number must be 16 characters long")
    private String cardNumber;
    @NotBlank(message = "Card holder is required")
    private String cardHolder;
    @Min(value = 2023,message = "Card expiration year must be at least current year")
    private int cardExpirationYear;
    @Min(value = 1,message = "Card expiration month must be between 1 and 12")
    @Max(value = 12,message = "Card expiration month must be between 1 and 12")
    private int cardExpirationMonth;
    @NotEmpty(message = "Card CVV is required")
    @Size(min = 3, max = 3, message = "Card CVV must be 3 characters long")
    private String cardCvv;
}
