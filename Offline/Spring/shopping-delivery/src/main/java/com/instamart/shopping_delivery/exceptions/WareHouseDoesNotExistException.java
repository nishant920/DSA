package com.instamart.shopping_delivery.exceptions;

public class WareHouseDoesNotExistException extends RuntimeException{
    public WareHouseDoesNotExistException(String message){
        super(message);
    }
}
