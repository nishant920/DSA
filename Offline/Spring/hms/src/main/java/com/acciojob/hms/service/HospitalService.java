package com.acciojob.hms.service;

import com.acciojob.hms.models.Doctor;
import com.acciojob.hms.models.Hospital;
import com.acciojob.hms.repositries.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    /**
     * Work of this function is to save hospital object in the database.
     * As it is service layer function inside this we will be calculating ID for the hospital
     * And then will be calling hospital repository to save hospital inside the database.
     * @param hospital
     */
    public void registerHospital(Hospital hospital){
        // 1st Step we need to calculate the id for this hospital.
        int id = hospitalRepository.getTotalHospital() + 1;
        hospital.setId(id);
        hospitalRepository.saveHospital(id, hospital);
    }


    public Hospital getHospitalById(int id){
        Hospital hospital = hospitalRepository.findHospitalById(id);
        return hospital;
    }

    public List<Hospital> getAllHospital(){
        HashMap<Integer, Hospital> map = hospitalRepository.getHospitalMap();
        List<Hospital> hospitals = new ArrayList<>();
        for(int key : map.keySet()){
            Hospital hospital = map.get(key);
            hospitals.add(hospital);
        }
        return hospitals;
    }

    /**
     * Below function will return the hospital which is having minimum number of doctors.
     */
    public Hospital getHospitalHavingMinimumDoctor(){
        List<Hospital> hospitals = this.getAllHospital();
        Hospital ans = null;
        int min = Integer.MAX_VALUE;
        for(int i  = 0; i < hospitals.size(); i++){
            Hospital hospital = hospitals.get(i);
            if(hospital.getDoctors().size() < min){
                min = hospital.getDoctors().size();
                ans = hospital;
            }
        }
        return ans;
    }


    public List<Doctor> getAllDoctorByHospitalId(int hospitalId){
       Hospital hospital =  this.getHospitalById(hospitalId);
       return hospital.getDoctors();
    }
}
