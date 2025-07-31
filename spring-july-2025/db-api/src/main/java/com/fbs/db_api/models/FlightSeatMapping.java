package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/*
This flight seat mapping model will only be used for non connecting flights
 */
@Data
@Entity
@Table(name = "flightseatmapping")
public class FlightSeatMapping extends SeatMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    // id	flightId	classname	range	baseprice	windowprice
    String className;
    String range; // 1-20
    int basePrice;
    int windowPrice;
    int totalWindow;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @ManyToOne
    Flight flight;
}
