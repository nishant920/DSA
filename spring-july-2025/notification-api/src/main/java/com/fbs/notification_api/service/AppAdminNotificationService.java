package com.fbs.notification_api.service;

import com.fbs.notification_api.dto.AirlineRegistrationReqDto;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;

@Service
@Slf4j
public class AppAdminNotificationService {

    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;

    @Autowired
    public AppAdminNotificationService(JavaMailSender javaMailSender,
                                       TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }


    public void sendAirlineRegistrationRequestNotification(AirlineRegistrationReqDto airlineRegistrationReqDto){
        // To send the mail we require javaMailSender Libraray Object
        // To send the mail we need to set mail content
        // To set mailcontent there is a class called MimeMessage so, we will set all the mail content into this Mimemessage class object
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // To set the values inside the Mimemessage we need MimeMessage helper class object
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        // Now by using the mimeMessage Helper class object we will be setting all the mail content
        Context context = new Context(); // This class object help us to set values of the variables present inside the html template
        context.setVariable("airlineName", airlineRegistrationReqDto.getAirline().getAirlineName());
        context.setVariable("companyName", airlineRegistrationReqDto.getAirline().getCompanyName());
        context.setVariable("website", airlineRegistrationReqDto.getAirline().getWebsite());
        context.setVariable("employees", airlineRegistrationReqDto.getAirline().getEmployees());
        context.setVariable("totalFlights", airlineRegistrationReqDto.getAirline().getTotalFlights());
        context.setVariable("airlineAdminName", airlineRegistrationReqDto.getAirline().getAdmin().getName());
        context.setVariable("adminEmail", airlineRegistrationReqDto.getAirline().getAdmin().getEmail());
        context.setVariable("requestedTime", airlineRegistrationReqDto.getAirline().getCreatedAt().toString());
        // We need to load the Html Template inside this function and populate value of all the variables
        // To load html template inside this function we will use library called Thymeleaf.
        // To load html template we require object of TemplateEngine class (Present Inside your Thymeleaf).
        // I want to get that TemplateEngine object from Springboot. So, we need to create a bean of thyemeaf class and store it inside the IOC container
        String htmlContent = templateEngine.process("airline-registration-request", context); // We use templateEngine.process method to load the template inside our java function
        try{
            mimeMessageHelper.setTo(airlineRegistrationReqDto.getAppAdmin().getEmail()); // By using this method we will the setting the mailId whom we want to send email
            mimeMessageHelper.setSubject(airlineRegistrationReqDto.getAirline().getAirlineName() + " Registration Request");
            // mimeMessageHelper.setText("Hey, There is the new Registration Request"); -> This line is sending normal email with normal body
            mimeMessageHelper.setText(htmlContent, true); // When we need to send html content in this setText method we will pass another boolean parameter.
            // If we are passing boolean parameter as true that means I am passing html content.
        }catch (Exception e){
            log.error(e.getMessage());
        }
        // By calling beloew method we will be sending email to the users.
        javaMailSender.send(mimeMessage);
    }
}
