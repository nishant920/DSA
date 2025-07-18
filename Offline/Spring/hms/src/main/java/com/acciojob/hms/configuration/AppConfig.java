package com.acciojob.hms.configuration;
import com.acciojob.hms.models.Doctor;
import com.acciojob.hms.models.Hospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class AppConfig {
       @Bean
       public HashMap<Integer, Hospital> getHospitalDB(){
               return new HashMap<>();
       }

       @Bean
       public HashMap<Integer, Doctor> getDoctorDB(){
           return new HashMap<>();
       }
}
