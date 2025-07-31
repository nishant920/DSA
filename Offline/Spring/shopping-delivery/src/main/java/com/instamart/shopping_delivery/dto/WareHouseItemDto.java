package com.instamart.shopping_delivery.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WareHouseItemDto {
    UUID productId;
    UUID wid;
    String productName;
    double price;
    int quantity;
    boolean isAvailable;
    double discount;
}
