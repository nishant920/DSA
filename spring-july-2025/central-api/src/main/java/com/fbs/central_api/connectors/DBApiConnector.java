package com.fbs.central_api.connectors;

import com.fbs.central_api.dto.AllUsersDto;
import com.fbs.central_api.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/*
    Purpose of this class is to connect with the db api endpoints.
 */
@Component
@Slf4j
public class DBApiConnector {

    RestTemplate restTemplate;

    @Autowired
    public DBApiConnector(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${db.api.url}")
    String dbApiBaseUrl; // For this variable pick the value for application.properties

    public AppUser callCreateUserEndpoint(AppUser user){
        log.info("Inside callCreateUserEndpoint method with user object: " + user.toString());
        //1. Create URL that you want to call
        String url = dbApiBaseUrl + "/user/create";
        //2. We want to tell what rest method we want to use and what request body we want to pass
        RequestEntity request = RequestEntity.post(url).body(user);
        log.info("Created request : " + request.toString());
        //3. Hit or make the request on post to do this step we click sendbutton
        // but here we are going to use a class called RestTemplate

        // Send Button(Postman) -> Resttemplate class exchange method
        log.info("Calling dbApi create user endpoint");
        ResponseEntity<AppUser> response = restTemplate.exchange(url, HttpMethod.POST, request, AppUser.class);
        log.info("Respose: " + response.toString());
        return response.getBody();
    }

    /*
    We will write one method and that method will be hitting request to database api create airline endpoint
     */
    public Airline callCreateAirlineEndpoint(Airline airline){
        log.info("Inside callCreateAirlineEndpoint with payload: " + airline.toString());
        // 1. Create url
        String url = dbApiBaseUrl + "/airline/create";
        // 2. create request
        RequestEntity request = RequestEntity.post(url).body(airline);
        // 3. Create resttemplate object
        // 4. By using restTemplate.exchange method to call this endpoint
        ResponseEntity<Airline> response = restTemplate.exchange(url, HttpMethod.POST, request, Airline.class);
        return response.getBody();
    }

    /*
    This function will make request to db-api callGetAllUsersByUserType endpoint such that we will get all the system admins from the users table.
     */

    public List<AppUser> callGetAllUsersByUserType(String userType){
        // Do, we have any this kind of endpoint developed in DB Api
        String url = dbApiBaseUrl + "/user/get/" + userType;
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<AllUsersDto> resp = restTemplate.exchange(url, HttpMethod.GET, request, AllUsersDto.class);
        return resp.getBody().getAppUsers();
    }

    public Airline callGetAirlineByIdEndpoint(UUID airlineId){
        String url = dbApiBaseUrl + "/airline/" + airlineId.toString();
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Airline> resp = restTemplate.exchange(url, HttpMethod.GET, request, Airline.class);
        return resp.getBody();
    }

    public Airline callUpdateAirlineEndpoint(Airline airline){
        String url = dbApiBaseUrl + "/airline/update";
        RequestEntity request = RequestEntity.put(url).body(airline);
        ResponseEntity<Airline> response = restTemplate.exchange(url, HttpMethod.PUT, request, Airline.class);
        return response.getBody();
    }

    public AppUser callUpdateUserEndpoint(AppUser user){
        String url = dbApiBaseUrl + "/user/update";
        RequestEntity request = RequestEntity.put(url).body(user);
        ResponseEntity<AppUser> resp = restTemplate.exchange(url, HttpMethod.PUT, request, AppUser.class);
        return resp.getBody();
    }

    public AppUser callGetUserByEmailEndpoint(String email){
        // Are we having any endpoint related to this.
        String url = dbApiBaseUrl + "/user/email/" + email;
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<AppUser> resp = restTemplate.exchange(url, HttpMethod.GET, request, AppUser.class);
        return resp.getBody();
    }

    public Airline callGetAirlineByAdminIdEndpoint(UUID adminId){
        String url = dbApiBaseUrl + "/airline/get/admin/" +  adminId.toString();
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Airline> resp = restTemplate.exchange(url, HttpMethod.GET, request, Airline.class);
        return resp.getBody();
    }

    public Aircraft callSaveAircraftEndpoint(Aircraft aircraft){
        String url = dbApiBaseUrl + "/aircraft/save";
        RequestEntity request = RequestEntity.post(url).body(aircraft);
        ResponseEntity<Aircraft> resp = restTemplate.exchange(url, HttpMethod.POST, request, Aircraft.class);
        return resp.getBody();
    }

    public Aircraft callGetAircraftById(UUID aircraftId){
        String url = dbApiBaseUrl + "/aircraft/" + aircraftId.toString();
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Aircraft> resp = restTemplate.exchange(url, HttpMethod.GET, request, Aircraft.class);
        return resp.getBody();
    }

    public Flight callCreateFlightEndpoint(Flight flight){
        String url = dbApiBaseUrl + "/flight/create";
        RequestEntity request = RequestEntity.post(url).body(flight);
        ResponseEntity<Flight> response = restTemplate.exchange(url, HttpMethod.POST, request, Flight.class);
        return response.getBody();
    }

    public FlightSeatMapping callCreateFlightSeatMapping(FlightSeatMapping flightSeatMapping){
        String url = dbApiBaseUrl + "/seatmapping/create";
        RequestEntity request = RequestEntity.post(url).body(flightSeatMapping);
        ResponseEntity<FlightSeatMapping> response = restTemplate.exchange(url, HttpMethod.POST, request, FlightSeatMapping.class);
        return response.getBody();
    }

    public SubFlight callCreateSubFlightEndpoint(SubFlight subFlight){
        String url = dbApiBaseUrl + "/subflight/create";
        RequestEntity request = RequestEntity.post(url).body(subFlight);
        ResponseEntity<SubFlight> response = restTemplate.exchange(url, HttpMethod.POST, request, SubFlight.class);
        return response.getBody();
    }

    public Object callSearchFlightEndpoint(String sourceAirport,
                                                 String destinationAirport,
                                                 String dateTime){
        // db Api endpoint
        sourceAirport = sourceAirport.replace(' ', '+');
        destinationAirport = destinationAirport.replace(' ', '+');
        dateTime = dateTime.replace(' ', '+');
        String url = dbApiBaseUrl + "/flight/search?" + "sourceAirport="+sourceAirport+"&" + "destinationAirport=" + destinationAirport +"&" + "dateTime=" + dateTime;
        log.info(url);
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
        return resp.getBody();
    }
}
