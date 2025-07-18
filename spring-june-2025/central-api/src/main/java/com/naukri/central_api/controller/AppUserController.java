package com.naukri.central_api.controller;

import com.naukri.central_api.dto.JobSearchFilterDto;
import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import com.naukri.central_api.dto.JwtTokenResponseDto;
import com.naukri.central_api.dto.LoginDto;
import com.naukri.central_api.exceptions.UnAuthorizedException;
import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.service.UserService;
import com.naukri.central_api.utility.AuthUtility;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/central/user")
public class AppUserController {

    UserService userService;
    AuthUtility authUtility;

    @Autowired
    public AppUserController(UserService userService,
                             AuthUtility authUtility){

        this.userService = userService;
        this.authUtility = authUtility;
    }

    @PostMapping("/register")
    public ResponseEntity registerJobApplicant(@RequestBody JobSeekerRegistrationDto jobSeekerDto){
        // calling user service
        AppUser user = userService.registerJobSeeker(jobSeekerDto);
        String token = authUtility.generateToken(user.getEmail(),
                user.getPassword(),
                user.getUserType());
        JwtTokenResponseDto tokenResp = new JwtTokenResponseDto(token);
        return new ResponseEntity(tokenResp, HttpStatus.CREATED);
    }


    @GetMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginDto loginDto){
        try{
            String token =  authUtility.generateTokenByLoginDetails(loginDto.getEmail(), loginDto.getPassword());
            return new ResponseEntity(new JwtTokenResponseDto(token), HttpStatus.OK);
        }catch (UnAuthorizedException e){
            return new ResponseEntity(e, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/job/search")
    public ResponseEntity searchJob(
            @RequestBody JobSearchFilterDto jobSearchFilterDto
            ){
        // callJobService


    }
}
