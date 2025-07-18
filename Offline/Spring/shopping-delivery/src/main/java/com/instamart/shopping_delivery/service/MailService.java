package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.models.AppUser;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Properties;

@Service
@Slf4j
public class MailService {

    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;

    @Autowired
    public MailService(JavaMailSender javaMailSender,
                       TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }


    public void sendWareHouseInvitationMail(AppUser wareHouseAdmin){
        String platformName = "Noida Evening Grocery App";
        String acceptLink = "http://localhost:8080/api/v1/user/warehouse/admin/accept/invite/" + wareHouseAdmin.getId().toString();
        Context context = new Context();
        context.setVariable("platformName", platformName);
        context.setVariable("warehouseAdminName", wareHouseAdmin.getName());
        context.setVariable("acceptLink", acceptLink);
        String htmlContent = templateEngine.process("warehouse-admin-invite", context);
        // Mime message ke andar hum mail ka content set karte hai
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // Mime message ke andar bhi hum directly content set nahi kar sakte hume banana hota hai mimeMessage helper
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try{
            mimeMessageHelper.setTo(wareHouseAdmin.getEmail());
            mimeMessageHelper.setSubject("Invitation to our swiggy platform as warehouse amdin");
            mimeMessageHelper.setText(htmlContent, true);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        javaMailSender.send(mimeMessage);
    }
}
