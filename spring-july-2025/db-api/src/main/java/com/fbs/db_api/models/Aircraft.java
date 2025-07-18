package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "aircrafts")
public class Aircraft {
    // id	AircraftName	ModelNumber	developerCompany	planeModel	totalFlighs	buildDate	airlineId	capacity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    int modelNumber;
    String manufacturer;
    String modelName;
    int totalFlights;
    LocalDate buildDate;
    @ManyToOne
    Airline airline;
    int capacity;
}

