package com.fbs.central_api.controllers;

import com.fbs.central_api.dto.AircraftRegistrationDto;
import com.fbs.central_api.dto.AirlineRegistrationDto;
import com.fbs.central_api.dto.FlightDetailsDto;
import com.fbs.central_api.models.Aircraft;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.models.Flight;
import com.fbs.central_api.service.AircraftService;
import com.fbs.central_api.service.AirlineService;
import com.fbs.central_api.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/airline")
@Slf4j
public class AirlineController {

    AirlineService airlineService;
    AircraftService aircraftService;

    FlightService flightService;
    @Autowired
    public AirlineController(AirlineService airlineService,
                             AircraftService aircraftService,
                             FlightService flightService){

        this.airlineService = airlineService;
        this.aircraftService = aircraftService;
        this.flightService = flightService;
    }

    /*
    This method will get called when this particular /api/v1/central/airline/register will get triggered
     */
    @PostMapping("/register")
    public ResponseEntity registerAirline(@RequestBody AirlineRegistrationDto airlineDetails){
        // Airline Details -> We need to catch that airline details json in an airlineDetailsDTo
        // From here we should call airlineService for further processing
        // From this method we are going to call
        log.info("airlineRegistration method got called with the requestbody : " + airlineDetails.toString());
        log.info("calling airlineService registerAirline method");
        // frm here we will call airline service register Airline method
        Airline airline = airlineService.registerAirline(airlineDetails);
        return new ResponseEntity(airline, HttpStatus.CREATED);
    }

    /*
    This endpoint will get triggered from the mail which we have sent to System Admin.
    When System Admin will click over the accept button this endpoint will get triggered.
    Work of this endpoint to change the status of Airline to ACTIVE also it will changing the status of airlineAdmin also to active
     */
    @GetMapping("/request/accept/{airlineId}")
    public void acceptAirlineRequest(@PathVariable UUID airlineId){
        log.info("airlineId : " + airlineId.toString());
        // we will be calling our airlineService to change the status of airline and airline admin to active
        airlineService.acceptAirlineRequest(airlineId);
    }

    @GetMapping("/request/reject/{airlineId}")
    public void rejectAirlineRequest(@PathVariable UUID airlineId){
        log.info("Reject Airline function: " + airlineId.toString());
        airlineService.rejectAirlineRequest(airlineId);
    }

    @PostMapping("/aircraft/register")
    public Aircraft registerAircraft(@RequestBody AircraftRegistrationDto aircraftRegistrationDto,
                                     @RequestHeader String Authorization){
        // We need to call the service
        return aircraftService.registerAircraft(aircraftRegistrationDto, Authorization);
    }

    @PostMapping("/flight/create")
    public Flight createFlight(@RequestBody FlightDetailsDto flightDetailsDto,
                               @RequestHeader String Authorization
                             ){
        return flightService.createFlight(flightDetailsDto, Authorization);
    }



}
