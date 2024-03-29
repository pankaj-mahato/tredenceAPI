package com.example.ecommerceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ShopperAlreadyExistsException extends RuntimeException{
    public ShopperAlreadyExistsException(String message) {
        super(message);
    }
}
