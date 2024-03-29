package com.example.ecommerceapp.controller;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.service.ProductService;
import com.example.ecommerceapp.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/datateam")
public class DataTeamController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopperService shopperService;

    @PostMapping("/add-product")
    public ResponseEntity<?> insertProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    @PostMapping("/remove-product")
    public ResponseEntity<?> removeProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.removeProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("/remove-product/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable String productId){
        return new ResponseEntity<>(productService.removeProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/get-product/{productId}")
    public ResponseEntity<?> showProduct(@PathVariable String productId){
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }


}
