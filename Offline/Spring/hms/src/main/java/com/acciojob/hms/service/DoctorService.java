package com.acciojob.hms.service;

import com.acciojob.hms.models.Doctor;
import com.acciojob.hms.models.Hospital;
import com.acciojob.hms.repositries.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {


    @Autowired
    HospitalService hospitalService;

    @Autowired
    DoctorRepository doctorRepository;

    /**
     * This particular method will recieve the same doctor object which our api has recieved from the client.
     * @param doctor
     */
    public Hospital registerDoctor(Doctor doctor){
        int id = doctorRepository.totalDoctors() + 1;
        doctor.setId(id);
        Hospital hospital = hospitalService.getHospitalHavingMinimumDoctor();
        hospital.getDoctors().add(doctor);
        doctorRepository.saveDoctor(id, doctor);
        return hospital;
    }

}
