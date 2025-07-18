package com.acciojob.hms.controllers;

import com.acciojob.hms.models.Doctor;
import com.acciojob.hms.models.Hospital;
import com.acciojob.hms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/register")
    public Hospital registerDoctor(@RequestBody Doctor doctor){
        // call doctor service
        return doctorService.registerDoctor(doctor);
    }
}
