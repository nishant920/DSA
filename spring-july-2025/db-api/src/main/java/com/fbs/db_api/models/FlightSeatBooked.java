package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/*
This booking table we are strictly going to use for non connecting flights
 */
@Data
@Entity
@Table(name = "flightbookedseats")
public class FlightSeatBooked extends SeatBooked {
    // id	flightId	seatnum	passengerId	bookingid
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Flight flight;
    @ManyToOne
    AppUser bookedBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
