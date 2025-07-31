package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.dto.WareHouseItemDto;
import com.instamart.shopping_delivery.dto.WareHouseRegistrationDto;
import com.instamart.shopping_delivery.enums.UserTypeEnum;
import com.instamart.shopping_delivery.exceptions.InvalidOperationException;
import com.instamart.shopping_delivery.models.*;
import com.instamart.shopping_delivery.repositories.WareHouseItemRepository;
import com.instamart.shopping_delivery.repositories.WareHouseRepository;
import com.instamart.shopping_delivery.utility.MappingUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WareHouseService {

    AppUserService appUserService;
    WareHouseRepository wareHouseRepository;
    LocationService locationService;
    MailService mailService;
    MappingUtility mappingUtility;
    ProductService productService;
    WareHouseItemRepository wareHouseItemRepository;
    @Autowired
    public WareHouseService(AppUserService appUserService,
                            WareHouseRepository wareHouseRepository,
                            LocationService locationService,
                            MappingUtility mappingUtility,
                            MailService mailService,
                            ProductService productService,
                            WareHouseItemRepository wareHouseItemRepository){
        this.appUserService = appUserService;
        this.wareHouseRepository = wareHouseRepository;
        this.locationService = locationService;
        this.mappingUtility = mappingUtility;
        this.mailService = mailService;
        this.productService = productService;
        this.wareHouseItemRepository = wareHouseItemRepository;
    }

    public WareHouse saveWareHouse(WareHouse wareHouse){
        return this.wareHouseRepository.save(wareHouse);
    }

    public WareHouse registerWareHouse(UUID userId,
                                  WareHouseRegistrationDto wareHouseRegistrationDto){
        // 1. Validate the Id belongs to app admin or not
        // So, we should what ?
        AppUser admin  = appUserService.isAppAdmin(userId);

        if(admin == null){
           throw new InvalidOperationException(String.format("User with id %s not allowed to register ware house", userId.toString()));
        }
        Location location = locationService.createLocation(wareHouseRegistrationDto.getLocation());
        // 2. Map details of wareHouse Registration DTO to WareHouse model.
        WareHouse wareHouse = mappingUtility.mapWareHouseToDtoToModel(wareHouseRegistrationDto, location);
        // 3. call Warehouse Repository to save warehouse in the warehouse table.
        wareHouse = this.saveWareHouse(wareHouse);
        // Notify Application admin that new warehouse got registered in your application.
        // We need mail service ->
        mailService.sendCreateWareHouseMail(wareHouse, admin);

        return wareHouse;
    }

    public WareHouse getWareHouseById(UUID wid){
        return wareHouseRepository.findById(wid).orElse(null);
    }

    public WareHouseItem assignProductToWareHouse(WareHouseItem wareHouseItem){
        UUID wid = wareHouseItem.getWid();
        UUID pid = wareHouseItem.getPid();
        // Validate both the ids are correct or not ?
        Product product = productService.isValidProduct(pid);
        int totalQuantity = product.getTotalQuantity();
        if(totalQuantity < wareHouseItem.getQuantity()){
            throw new InvalidOperationException("Can't assign quantity to warehouse as the requested quantity is mpre then the actual qunatity");
        }
        product.setTotalQuantity(totalQuantity - wareHouseItem.getQuantity());
        productService.updateProduct(product);
        wareHouseItem.setCreatedAt(LocalDateTime.now());
        wareHouseItem.setUpdatedAt(LocalDateTime.now());
        // Save WareHouse Item in table -> WarehouseItem
        wareHouseItem = wareHouseItemRepository.save(wareHouseItem);
        WareHouse wareHouse = getWareHouseById(wid);
        wareHouse.getWareHouseItems().add(wareHouseItem);
        saveWareHouse(wareHouse);
        return wareHouseItem;
    }

    public WareHouse assignManagerToWareHouse(UUID adminId,
                                              UUID wareHouseId,
                                              UUID wareHouseAdminId){
        // Verify all the ids
        AppUser admin = appUserService.getUserById(adminId);
        AppUser wareHouseAdmin = appUserService.getUserById(wareHouseAdminId);
        WareHouse wareHouse = getWareHouseById(wareHouseId);
        wareHouse.setManager(wareHouseAdmin);
        saveWareHouse(wareHouse);
        // Mail Service -> Notify WareHouse admin that a ware house is assigned to him
        return wareHouse;
    }

    public WareHouse findWareHouseAtPincode(int pincode){
        // We should check warehouse table and check is there any warehouse at this pincode.
        UUID wareHouseId  = wareHouseRepository.getWareHouseByLocation(pincode);
        if(wareHouseId == null){
            return null;
        }
        return this.getWareHouseById(wareHouseId);
    }

    public WareHouse getWareHouseByCustomerId(UUID customerId){
        AppUser customer = appUserService.getUserById(customerId);
        if(!customer.getUserType().equals(UserTypeEnum.CUSTOMER.toString())){
            throw new InvalidOperationException(String.format("User with id %s is not allowed to see all products", customerId.toString()));
        }
        Location location = locationService.getUserPrimaryLocation(customer);
        int pincode = location.getPinCode();
        WareHouse wareHouse = this.findWareHouseAtPincode(pincode);
        return wareHouse;
    }

    public List<Product> getAllProductsByPincode(UUID customerId){
        WareHouse wareHouse = getWareHouseById(customerId);
        List<WareHouseItem> wareHouseItems = wareHouse.getWareHouseItems();
        List<Product> products = new ArrayList<>();
        for(WareHouseItem wareHouseItem : wareHouseItems){
            UUID pid = wareHouseItem.getPid();
            // Product Service
            Product product = productService.getProductById(pid);
            products.add(product);
        }
        return products;
    }

    public List<WareHouseItemDto> getProductsAtPincodeByName(String name,
                                           UUID customerId){
        // product service
        List<Product> products = productService.getProductsByName(name);
        WareHouse wareHouse = getWareHouseByCustomerId(customerId);
        List<WareHouseItem> wareHouseItems = wareHouse.getWareHouseItems();
        List<WareHouseItemDto> wareHouseItemDtos = new ArrayList<>();
        for(int i  = 0; i < products.size(); i++){
            UUID productId = products.get(i).getId();
            String productName = products.get(i).getProductName();
            WareHouseItemDto wareHouseItemDto = new WareHouseItemDto();
            wareHouseItemDto.setProductName(productName);
            wareHouseItemDto.setDiscount(0.0);
            wareHouseItemDto.setAvailable(false);
            wareHouseItemDto.setWid(wareHouse.getId());
            wareHouseItemDto.setPrice(products.get(i).getUnitPrice());
            for(int j = 0; j < wareHouseItems.size(); j++){
                UUID itemProductId = wareHouseItems.get(j).getPid();
                if(productId.toString().equals(itemProductId.toString())){
                   wareHouseItemDto.setAvailable(true);
                   wareHouseItemDto.setDiscount(wareHouseItems.get(j).getDiscount());
                }
            }

            wareHouseItemDtos.add(wareHouseItemDto);

        }


        return wareHouseItemDtos;

    }
}
