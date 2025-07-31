package com.instamart.shopping_delivery.controllers;

import com.instamart.shopping_delivery.exceptions.InvalidOperationException;
import com.instamart.shopping_delivery.exceptions.UserNotExistException;
import com.instamart.shopping_delivery.exceptions.WareHouseDoesNotExistException;
import com.instamart.shopping_delivery.models.AppUser;
import com.instamart.shopping_delivery.service.AppUserService;
import com.instamart.shopping_delivery.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    AppUserService appUserService;
    DeliveryPartnerService deliveryPartnerService;

    @Autowired
    public UserController(AppUserService appUserService,
                          DeliveryPartnerService deliveryPartnerService){
        this.deliveryPartnerService = deliveryPartnerService;
        this.appUserService = appUserService;
    }

    @PostMapping("/customer/registration")
    public AppUser customerRegistration(@RequestBody AppUser customer){
        System.out.println(customer);
        // CustomerService
        AppUser user = appUserService.registerUser(customer);
        return user;
    }

    @PostMapping("/warehouse/admin/invite")
    public ResponseEntity wareHouseAdminInvite(@RequestParam UUID userId,
                                               @RequestBody AppUser wareHouseAdmin){
        // appuserservice -> Warehouse admin invite
        try{
            appUserService.wareHouseAdminInvite(userId, wareHouseAdmin);
            return new ResponseEntity("Inactive record created inside users table.", HttpStatus.CREATED);
        }catch (InvalidOperationException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch (UserNotExistException e){
           return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/warehouse/admin/accept/invite/{wareHouseAdminId}")
    public void acceptWareHouseAdminInvite(@PathVariable UUID wareHouseAdminId){
        appUserService.acceptWareHouseAdminInvite(wareHouseAdminId);
    }

    /**
     * This particular function will recieve request from the client to register delivery partner.
      * @param deliveryPartner
     */
    @PostMapping("/deliverypartner/registration")
    public ResponseEntity deliveryPartnerRegistration(@RequestBody AppUser deliveryPartner){
        // DeliveryPartnerService
        try{
            deliveryPartner = deliveryPartnerService.registerDeliveryPartner(deliveryPartner);
            return new ResponseEntity<>(deliveryPartner, HttpStatus.CREATED);
        }catch (WareHouseDoesNotExistException wareHouseDoesNotExistException){
            return new ResponseEntity<>(wareHouseDoesNotExistException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



}
