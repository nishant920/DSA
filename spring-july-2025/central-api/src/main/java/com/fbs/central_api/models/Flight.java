package com.fbs.central_api.models;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flight {
    // id	airlineId	aircraftId	source Airport	destination airport	flight type	totalTime	boarding time	departure time	arrival time	isConnecting
    UUID id;
    Airline airline;
    Aircraft aircraft;
    String sourceAirport; // Mumbai
    String destinationAirport; // New York
    String flightType; // International, Domestic, Emergency
    int totalTime;// TotalTime in minutes
    LocalDateTime boardingTime; // When you passengers need to sit in the aircraft
    int boardingMinutes;
    LocalDateTime departureTime; // When aircraft is going to takeoff; // IST TimeZone
    LocalDateTime arrivalTime; // When aircreaft is going to land // EST Timezone
    boolean isConnecting; // is this flight a connecting flight ? or not
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
