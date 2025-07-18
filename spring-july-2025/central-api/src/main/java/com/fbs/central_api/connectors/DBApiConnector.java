package com.fbs.central_api.connectors;

import com.fbs.central_api.dto.AllUsersDto;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

}
