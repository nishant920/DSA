package com.fbs.central_api.service;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.FlightDetailsDto;
import com.fbs.central_api.dto.SeatMappingDto;
import com.fbs.central_api.dto.SubFlightDto;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.exceptions.UnAuthorizedException;
import com.fbs.central_api.models.*;
import com.fbs.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FlightService {

    UserService userService;
    AirlineService airlineService;
    AircraftService aircraftService;
    Mapper mapper;
    DBApiConnector dbApiConnector;
    @Autowired
    public FlightService(UserService userService,
                         Mapper mapper,
                         AirlineService airlineService,
                         AircraftService aircraftService,
                         DBApiConnector dbApiConnector){
        this.userService = userService;
        this.mapper = mapper;
        this.airlineService = airlineService;
        this.aircraftService = aircraftService;
        this.dbApiConnector = dbApiConnector;
    }

    public void createSubFlights(List<SubFlightDto> subFlightDtos, Flight flight){
        for(SubFlightDto subFlightDto : subFlightDtos){
            // We need to map subFlightDto one by one to SubFlightModel
            SubFlight subFlight = mapper.mapSubFlightDtoToSubFlightModel(subFlightDto, flight);
            // db Api connector to save subFlight into the database.
            dbApiConnector.callCreateSubFlightEndpoint(subFlight);
        }
    }

    public Flight createFlight(FlightDetailsDto flightDetailsDto,
                             String authorization){
        String token = authorization.substring(7);
        AppUser user = userService.getUserFromToken(token);
        if(!user.getUserType().equals(UserType.AIRLINE_ADMIN.toString())){
            throw new UnAuthorizedException("User is not allowed to create flight");
        }
        Airline airline = airlineService.getAirlineByAdminId(user.getId());
        Aircraft aircraft = aircraftService.getAirCraftrById(flightDetailsDto.getAirCraftID());

        Flight flight = mapper.mapFlightDetailsDtoToFightModel(flightDetailsDto, airline, aircraft);
        // We need to save the flight inside the database.
        flight = dbApiConnector.callCreateFlightEndpoint(flight);
        // We need to create different classes for the flight
        if(flightDetailsDto.getSubFlightDtos().size() > 0){
            createSubFlights(flightDetailsDto.getSubFlightDtos(), flight);
        }
        List<SeatMappingDto> seatMappingDtos = flightDetailsDto.getSeatMappingDtos();
        for(int i = 0; i < seatMappingDtos.size(); i++){
            SeatMappingDto seatMappingDto = seatMappingDtos.get(i);
            // Mapper
            FlightSeatMapping flightSeatMapping = mapper.mapFlightSeatMappingDtoToModel(seatMappingDto, flight);
            // dbApiConnector to save the flightSeatMapping
            dbApiConnector.callCreateFlightSeatMapping(flightSeatMapping);
        }
        // Mail -> We need to mail to the flight Admin that your requested flight got created inside the system.
        return flight;
    }

    public Object searchFlight(String sourceAirport,
                                     String destinationAirport,
                                     String localDateTime){
        // dbApiConnector
        return dbApiConnector.callSearchFlightEndpoint(sourceAirport, destinationAirport, localDateTime.toString());
    }
}
