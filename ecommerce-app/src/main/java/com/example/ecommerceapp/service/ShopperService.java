package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.Shelfitem;
import com.example.ecommerceapp.model.Shopper;
import com.example.ecommerceapp.model2.ShelfitemDTO;
import com.example.ecommerceapp.model2.ShopperDTO;

import java.util.List;

public interface ShopperService {


    public abstract Shopper shopperDTOtoShopperConverter(ShopperDTO s);
    public abstract List<Shelfitem> shopperDTOtoShelfitemConverter(ShopperDTO s);

    public abstract void addShopperDTO(ShopperDTO s);
    public abstract void removeShopper(String shopperId);
    public abstract void updateShopperDTO(ShopperDTO shopperDTO);



    public abstract ShopperDTO addShelfItemToShopper(ShelfitemDTO s);

    public ShopperDTO getProductsByShopperId(String shopperId);

    public abstract ShopperDTO getProductsByShopperWithFilters(String shopperId, String category, String brand, int limit);
}
