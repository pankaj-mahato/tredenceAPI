package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shelfitem;
import com.example.ecommerceapp.model.Shopper;
import com.example.ecommerceapp.model2.ShopperDTO;

import java.util.List;

public interface ShopperService {
    public abstract void addShopper(Shopper s);
    public abstract void removeShopper(Shopper s);
    public abstract void removeShopper(String shopperId);
    public abstract void updateShopper(Shopper s);



    public abstract Shelfitem addShelfItem(Shelfitem s);

    public ShopperDTO getProductsByShopperId(String shopperId);

    public abstract ShopperDTO getProductsByShopperWithFilters(String shopperId, String category, String brand, int limit);
}
