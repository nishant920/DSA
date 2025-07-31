package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.dto.ProductDto;
import com.instamart.shopping_delivery.enums.UserTypeEnum;
import com.instamart.shopping_delivery.exceptions.InvalidOperationException;
import com.instamart.shopping_delivery.models.AppUser;
import com.instamart.shopping_delivery.models.Location;
import com.instamart.shopping_delivery.models.Product;
import com.instamart.shopping_delivery.repositories.ProductRepository;
import com.instamart.shopping_delivery.utility.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    AppUserService appUserService;
    ProductRepository productRepository;

    MappingUtility mappingUtility;
    LocationService locationService;


    @Autowired
    public ProductService(AppUserService appUserService,
                          ProductRepository productRepository,
                          MappingUtility mappingUtility,
                          LocationService locationService){
        this.appUserService = appUserService;
        this.productRepository = productRepository;
        this.mappingUtility = mappingUtility;
        this.locationService = locationService;
    }

    public Product saveProduct(Product product){
        return this.productRepository.save(product);
    }

    public ProductDto addProduct(ProductDto productDto,
                           UUID userId){
        AppUser user = appUserService.isAppAdmin(userId);
        if(user == null){
            throw new InvalidOperationException(String.format("User with id %s does not have access to create product.", userId.toString()));
        }
        // Now we want to save Product n product table.
        // So, to save product in product table we require product repository
        // As we can save product model object in the the product table.
        // So, we need to map product dto to product model .
        Product product = this.mappingUtility.mapProductDtoToProduct(productDto, user);
        product = this.saveProduct(product);
        productDto.setId(product.getId());
        return productDto;
    }

    public void updateProduct(Product product){
        productRepository.save(product);
    }

    public Product isValidProduct(UUID productId){
        return productRepository.findById(productId).orElse(null);
    }

    public Product getProductById(UUID productId){
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> getProductsByName(String name){
        // product repositoru
        String namePattern = "%" +  name + "%";
        return productRepository.getProductByName(namePattern);
    }

}
