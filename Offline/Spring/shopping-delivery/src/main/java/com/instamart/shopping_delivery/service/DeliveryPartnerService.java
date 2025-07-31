package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.enums.UserStatusEnum;
import com.instamart.shopping_delivery.exceptions.WareHouseDoesNotExistException;
import com.instamart.shopping_delivery.models.AppUser;
import com.instamart.shopping_delivery.models.Location;
import com.instamart.shopping_delivery.models.WareHouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryPartnerService {

    WareHouseService wareHouseService;
    LocationService locationService;
    AppUserService appUserService;

    MailService mailService;

    @Autowired
    public DeliveryPartnerService(WareHouseService wareHouseService,
                                  LocationService locationService,
                                  AppUserService appUserService,
                                  MailService mailService){
        this.wareHouseService = wareHouseService;
        this.locationService = locationService;
        this.appUserService = appUserService;
        this.mailService = mailService;
    }
    /**
     * This function contain logic to saveDelivery partner object inside the database.
     * And we will have status of delivery partner as inactive.
     * And we will be mailing to the wareHouseAdmin regarding delivery partner registration.
     */
    public AppUser registerDeliveryPartner(AppUser deliveryPartner){
        // Customer can have multiple location but delivery partner
        int pinCode = deliveryPartner.getLocations().get(0).getPinCode();
        // Find Warehouse at pincode.
        WareHouse wareHouse = wareHouseService.findWareHouseAtPincode(pinCode);
        if(wareHouse == null){
            throw new WareHouseDoesNotExistException(String.format("Warehouse at pincode %d does not exist", pinCode));
        }
        // Database -> Delivery Partner ke object ko create karna hai
        deliveryPartner.setStatus(UserStatusEnum.INACTIVE.toString());
        Location location = deliveryPartner.getLocations().get(0);
        // Save to database.
        location = locationService.createLocation(location);
        deliveryPartner.getLocations().set(0, location);
        // delivery partner ko save karna hai user table ke andar
        deliveryPartner = appUserService.registerUser(deliveryPartner);
        // Now we need to mail warehouse admin regarding delivery partner registration.
        mailService.sendDeliveryPartnerRegistrationMailToWareHouseAdmin(deliveryPartner, wareHouse.getManager().getEmail());
        return deliveryPartner;
    }
}
