package com.fbs.central_api.service;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.AirlineRegistrationDto;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AirlineService {

    Mapper mapper;
    DBApiConnector dbApiConnector;

    UserService userService;

    MailService mailService;

    @Autowired
    public AirlineService(Mapper mapper,
                          DBApiConnector dbApiConnector,
                          UserService userService,
                          MailService mailService){
        this.mapper = mapper;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
        this.mailService = mailService;
    }

    /*
        This function work is to call db api and save airline details in airline table and airline admins details in users table.
    */
    public Airline registerAirline(AirlineRegistrationDto airlineRegistrationDto){
        log.info("airlineService registerAirline method called: " + airlineRegistrationDto.toString());
        // before calling db api lets map the details which we are getting in dto to the respective models
        // Ideally we should not write mapping logic here we should keep it in different class
        AppUser airlineAdmin = mapper.mapAirlineDetailsDtoToAppUser(airlineRegistrationDto);
        // After creating airline admin object we should call db api to save this object to the table.
        // That means we need to connect with db api AppUser registration endpoint
        // So, to connect with the dbapi app user registration endpoint we should create connector class
        log.info("Calling dbApiConnector callCreateUserMethod with payload:  " + airlineAdmin.toString());
        airlineAdmin = dbApiConnector.callCreateUserEndpoint(airlineAdmin);
        // Mapping airlineRegistrationDto to Airline object -> We need to write another mapper.
        Airline airline = mapper.mapAirlineDetailsDtoToAirlineObject(airlineRegistrationDto, airlineAdmin);
        // Now we got the airline object we need to save this airline into the airline table.
        // So, to this airline into airline table we need to call database api connector
        // Internally dbapiconnector will be calling your create airline endpoint
        airline = dbApiConnector.callCreateAirlineEndpoint(airline);
        // When we have created both the inactive records for airline as well as airline admin
        // We to mail app admin that this airline is trying to register into your application.
        // We need to think something how we can mail ?
        // We will be creating another microservice whoose work is to send notifications to the user via mail
        // Now we need to mail application admin regarding airline registration request
        // So, to mail we require application admin object
        // We need to mail all the system admins so, we need to get all the system admins from the table.
        List<AppUser> systemAdminList = userService.getAllSystemAdmins();
        // Mail all system admins
        mailService.mailSystemAdminForAirlineRegistration(systemAdminList, airline);

        return airline;
    }
}
