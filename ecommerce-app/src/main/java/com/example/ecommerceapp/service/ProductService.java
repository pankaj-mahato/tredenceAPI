package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.Product;

import java.util.List;

public interface ProductService {
    public abstract Product addProduct(Product p);
    public abstract boolean removeProduct(Product p);
    public abstract boolean removeProduct(String productId);
    public abstract Product getProduct(String productId);
}
