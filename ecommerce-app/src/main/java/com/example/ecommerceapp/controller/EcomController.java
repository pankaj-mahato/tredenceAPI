package com.example.ecommerceapp.controller;

import com.example.ecommerceapp.service.ProductService;
import com.example.ecommerceapp.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ecom")
public class EcomController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopperService shopperService;

}
