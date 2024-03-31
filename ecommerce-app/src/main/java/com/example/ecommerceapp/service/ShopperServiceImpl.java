package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shelfitem;
import com.example.ecommerceapp.model.Shopper;
import com.example.ecommerceapp.model2.ShelfitemDTO;
import com.example.ecommerceapp.model2.ShopperDTO;
import com.example.ecommerceapp.repository.ProductRepository;
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
    @Autowired
    ProductRepository productRepository;

    @Override
    public Shopper shopperDTOtoShopperConverter(ShopperDTO s){
        Shopper shopper = new Shopper();
        shopper.setShopperId(s.getShopperId());
        return shopper;
    }

    @Override
    public List<Shelfitem> shopperDTOtoShelfitemConverter(ShopperDTO shopperDTO){
        int countShelves=shopperDTO.getShelf().size();
        int n = 0;
        List<Shelfitem> shelfitems= new ArrayList<>();
        while(n< countShelves){
            Shelfitem s = new Shelfitem();
            s.setShopperId(shopperDTO.getShopperId());
            s.setRelevancyScore(shopperDTO.getShelf().get(n).getRelevancyScore());
            s.setProductId(shopperDTO.getShelf().get(n).getProductId());
            shelfitems.add(s);
            n++;
        }
        return shelfitems;
    }

    @Override
    public void addShopperDTO(ShopperDTO shopperDTO) {
        Shopper s = shopperDTOtoShopperConverter(shopperDTO);
        shopperRepository.save(s);
        List<Shelfitem> shelfitems = shopperDTOtoShelfitemConverter(shopperDTO);
        int n=0;
        while(n<shelfitems.size()){
            shelfitemRepository.save(shelfitems.get(n));
            n++;
        }
    }

    @Override
    public void removeShopper(String shopperId) {
        shelfitemRepository.deleteByShopperId(shopperId);
        shopperRepository.deleteById(shopperId);
    }

    @Override
    public void updateShopperDTO(ShopperDTO shopperDTO) {
        //USE EXCEPTION IF SHOPPER IS NOT FOUND
//        Shopper shopper=shopperRepository.findById(shopperDTO.getShopperId()).get();
        List<Shelfitem> shelfitems = shopperDTOtoShelfitemConverter(shopperDTO);
        int n=0;
        while(n<shelfitems.size()){
            shelfitemRepository.save(shelfitems.get(n));
            n++;
        }
    }

    @Override
    public ShopperDTO addShelfItemToShopper(ShelfitemDTO shelfitemDTO) {
        //addShelfItemToShopper
        Shelfitem s = new Shelfitem();
        s.setShopperId(shelfitemDTO.getShopperId());
        s.setRelevancyScore(shelfitemDTO.getRelevancyScore());
        s.setProductId(shelfitemDTO.getProductId());
        shelfitemRepository.save(s);
        //return whole shopperdto
        ShopperDTO shopperDTO = new ShopperDTO();
        shopperDTO.setShopperId(shelfitemDTO.getShopperId());
        shopperDTO.setShelf(shelfitemRepository.findByShopperId(shelfitemDTO.getShopperId()));
        return shopperDTO;
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
        //step-01 Find the shopper
        //if not available throw exception
        Shopper s = shopperRepository.findById(shopperId).get();
        //step-02 find what are there in shopper list
        List<Shelfitem> shelfitems = shelfitemRepository.findByShopperId(shopperId);
            //remove the products whose category & brand doesn't match
        Iterator iterator=shelfitems.iterator();
        while (iterator.hasNext()){
            Shelfitem shelfitem = (Shelfitem) iterator.next();
            Product p = productRepository.findById(shelfitem.getProductId()).get();
            //If product not found then throw ProductNotFoundException
            //if product doesn't match then remove
            if(!p.getBrand().contains(brand)  && !p.getCategory().contains(category)){
                iterator.remove();
            }
        }
        //limit the list
        if(shelfitems.size()>limit){
            while (shelfitems.size()>limit){
                shelfitems.remove(shelfitems.size()-1);
            }
        }
        //Now we got the result as shelfitems
        //Now we have to convert the shelfitems to shopperdto object
        return null;
    }

}
