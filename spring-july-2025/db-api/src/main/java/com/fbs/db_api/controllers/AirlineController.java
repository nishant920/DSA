package com.fbs.db_api.controllers;

import com.fbs.db_api.models.Airline;
import com.fbs.db_api.repositories.AirlineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
   As this is simple crud endpoint controller and in db api we don't write any logics,
   So, we don't require any service class we will be directly calling repository layer.
 */
@RestController
@RequestMapping("/api/v1/db/airline")
@Slf4j
public class AirlineController {

    AirlineRepository airlineRepository;

    @Autowired
    public AirlineController(AirlineRepository airlineRepository){
        this.airlineRepository = airlineRepository;
    }


    @PostMapping("/create")
    public ResponseEntity createAirline(@RequestBody Airline airline){
        // To save airline we require airline repository
        log.info("RequestBody: " + airline.toString());
        Airline airlineResp = airlineRepository.save(airline);
        return new ResponseEntity(airlineResp, HttpStatus.CREATED);
    }
}
