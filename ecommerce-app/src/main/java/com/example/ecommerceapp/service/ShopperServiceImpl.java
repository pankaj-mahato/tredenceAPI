package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shelfitem;
import com.example.ecommerceapp.model.Shopper;
import com.example.ecommerceapp.model2.ShopperDTO;
import com.example.ecommerceapp.repository.ShelfitemRepository;
import com.example.ecommerceapp.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class ShopperServiceImpl implements ShopperService{
    @Autowired
    ShopperRepository shopperRepository;
    @Autowired
    ShelfitemRepository shelfitemRepository;

    @Override
    public void addShopper(Shopper s) {
        shopperRepository.save(s);
    }

    @Override
    public void removeShopper(Shopper s) {
        shopperRepository.deleteById(s.getShopperId());
    }

    @Override
    public void removeShopper(String shopperId) {
        shopperRepository.deleteById(shopperId);
    }

    @Override
    public void updateShopper(Shopper s) {
        shopperRepository.save(s);
    }

    @Override
    public Shelfitem addShelfItem(Shelfitem shelfitem) {
        return shelfitemRepository.save(shelfitem);
    }

    @Override
    public ShopperDTO getProductsByShopperId(String shopperId) {
        List<Shelfitem> allShelfitems=shelfitemRepository.findAll();
        List<Shelfitem> shelfitems = new ArrayList<>();
        Iterator itr=allShelfitems.iterator();
        while (itr.hasNext()){
            Shelfitem s=(Shelfitem) itr;
            if(s.getShopperId().equalsIgnoreCase(shopperId)) shelfitems.add(s);
        }
        return new ShopperDTO(shopperId, shelfitems);
    }

    @Override
    public ShopperDTO getProductsByShopperWithFilters(String shopperId, String category, String brand, int limit) {
        return null;
    }
}
