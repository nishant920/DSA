package com.instamart.shopping_delivery.controllers;

import com.instamart.shopping_delivery.dto.ProductDto;
import com.instamart.shopping_delivery.dto.WareHouseItemDto;
import com.instamart.shopping_delivery.exceptions.InvalidOperationException;
import com.instamart.shopping_delivery.models.Product;
import com.instamart.shopping_delivery.models.WareHouseItem;
import com.instamart.shopping_delivery.service.ProductService;
import com.instamart.shopping_delivery.service.WareHouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    ProductService productService;
    WareHouseService wareHouseService;

    @Autowired
    public ProductController(ProductService productService,
                             WareHouseService wareHouseService){
        this.productService = productService;
        this.wareHouseService = wareHouseService;
    }

    /*
    This function will run when /api/v1/product/add endoint will get triggered.
     */
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto,
                                     @RequestParam UUID userId){
        // We need to call product service to add product
        ProductDto product = productService.addProduct(productDto,userId);
        return new ResponseEntity(product, HttpStatus.CREATED);
    }

    @PostMapping("/assign")
    public ResponseEntity assignProductToWareHouse(@RequestBody WareHouseItem wareHouseItem){
        // Service
       wareHouseItem = wareHouseService.assignProductToWareHouse(wareHouseItem);
       return new ResponseEntity(wareHouseItem, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity getAllProductsByPincode(@RequestParam UUID customerId){
        // ProductService
        try{
            List<Product> products = wareHouseService.getAllProductsByPincode(customerId);
            return new ResponseEntity(products, HttpStatus.OK);
        }catch (InvalidOperationException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(),  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public List<WareHouseItemDto> getAllProductsByName(@RequestParam String name,
                                                       @RequestParam UUID customerId){
        //  WareHouse Service

        return wareHouseService.getProductsAtPincodeByName(name, customerId);
    }


}
