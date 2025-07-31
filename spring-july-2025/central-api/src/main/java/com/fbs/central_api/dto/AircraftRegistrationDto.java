package com.fbs.central_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AircraftRegistrationDto {
    int modelNumber;
    String manufacturer;
    String modelName;
    int totalFlights;
    LocalDate buildDate;
    int capacity;
}
