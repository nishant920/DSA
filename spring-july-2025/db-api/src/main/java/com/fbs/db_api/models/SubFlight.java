package com.fbs.db_api.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subflights")
public class SubFlight {
     //id	flightid	starting 	ending	layover	boarding time	departure time	arrival time
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Flight flight;
    int priority;
    String sourceAirport;
    String destinationAirport;
    LocalDateTime boardingTime;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime; // Where this subflight will land;
    int boardingMinutes;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
