package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;


    @Override
    public Product addProduct(Product p) {
        productRepository.save(p);
        return p;
    }

    @Override
    public boolean removeProduct(Product p) {
        productRepository.deleteById(p.getProductId());
        return true;
    }

    @Override
    public boolean removeProduct(String productId) {
        productRepository.deleteById(productId);
        return true;
        //also we have to delete this product from all shopper's shelf-list
//        shopperService.removeProductFromAllShopperList(productId);
    }

    @Override
    public Product getProduct(String productId) {
        return productRepository.findById(productId).get();
    }


}
