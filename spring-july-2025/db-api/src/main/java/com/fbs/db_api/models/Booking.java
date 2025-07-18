package com.fbs.db_api.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/*
This class is going to represent booking details
Direct Flight -> Delhi to Mumbai (subFlight list will be empty)
Connecting Flight -> SubFlight list will have all the subflight passenger is going to cover
// Delhi to Mumbai to Chandigarh to Sikkim
// subflight - > [(Delhi to mumbai), (mumbai to chandigarh)]
 */
@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Flight flight;
    @ManyToMany
    List<SubFlight> subFlights;
    @ManyToOne
    AppUser bookedBy;
    int totalAmount;
    String passengerName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
