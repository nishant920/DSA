package com.acciojob.hms.repositries;

import com.acciojob.hms.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.HashMap;

@Repository
public class DoctorRepository {
    @Autowired
    HashMap<Integer, Doctor> doctorDB;

    public void saveDoctor(int id, Doctor doctor){
        this.doctorDB.put(id, doctor);
    }

    public Doctor getDoctorById(int id){
        return this.doctorDB.get(id);
    }

    public int totalDoctors(){
        return this.doctorDB.size();
    }

}
