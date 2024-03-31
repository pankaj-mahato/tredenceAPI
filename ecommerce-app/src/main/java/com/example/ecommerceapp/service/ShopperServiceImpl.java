package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shelfitem;
import com.example.ecommerceapp.model.Shopper;
import com.example.ecommerceapp.model2.ShelfitemDTO;
import com.example.ecommerceapp.model2.ShelfitemInputDTO;
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
        shelfitemRepository.deleteAllByShopperId(shopperId);
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
    public ShopperDTO addShelfItemToShopper(ShelfitemInputDTO shelfitemInputDTO) {
        //addShelfItemToShopper
        Shelfitem s = new Shelfitem();
        s.setShopperId(shelfitemInputDTO.getShopperId());
        s.setRelevancyScore(shelfitemInputDTO.getRelevancyScore());
        s.setProductId(shelfitemInputDTO.getProductId());
        shelfitemRepository.save(s);
        //return whole shopperDTO
        ShopperDTO shopperDTO = new ShopperDTO();
        List<Shelfitem> shelfitems = shelfitemRepository.findAllByShopperId(shelfitemInputDTO.getShopperId());
        List<ShelfitemDTO> shelfitemDTOS = new ArrayList<>();
        shopperDTO.setShopperId(shelfitemInputDTO.getShopperId());
        int n=0;
        while(n<shelfitems.size()){
            ShelfitemDTO shelfitemDTO = new ShelfitemDTO();
            shelfitemDTO.setRelevancyScore(shelfitems.get(n).getRelevancyScore());
            shelfitemDTO.setProductId(shelfitems.get(n).getShopperId());
            n++;
            shelfitemDTOS.add(shelfitemDTO);
        }
        shopperDTO.setShelf(shelfitemDTOS);
        return shopperDTO;
    }

    @Override
    public ShopperDTO getProductsByShopperId(String shopperId) {
        List<Shelfitem> shelfitems = shelfitemRepository.findAllByShopperId(shopperId);
        List<ShelfitemDTO> shelfitemDTOS = new ArrayList<>();
        int n=0;
        while(n< shelfitems.size()){
            ShelfitemDTO shelfitemDTO = new ShelfitemDTO();
            shelfitemDTO.setProductId(shelfitems.get(n).getProductId());
            shelfitemDTO.setRelevancyScore(shelfitems.get(n).getRelevancyScore());
            n++;
        }
        return new ShopperDTO(shopperId, shelfitemDTOS);
    }

    @Override
    public ShopperDTO getProductsByShopperWithFilters(String shopperId, String category, String brand, int limit) {
        //step-01 Find the shopper
        //if not available throw exception
        Shopper s = shopperRepository.findById(shopperId).get();
        //step-02 find what are there in shopper list
        List<Shelfitem> shelfitems = shelfitemRepository.findAllByShopperId(shopperId);
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
        //Now we got the result as List<Shelfitem>
        //We need to convert the List<Shelfitem> to List<ShelfitemDTO>
        ShopperDTO shopperDTO = new ShopperDTO();
        shopperDTO.setShopperId(shopperId);
        List<ShelfitemDTO> shelfitemDTOS = new ArrayList<>();
        int i=0;
        while (i< shelfitems.size()){
            ShelfitemDTO shelfitemDTO = new ShelfitemDTO();
            shelfitemDTO.setProductId( shelfitems.get(i).getProductId() );
            shelfitemDTO.setRelevancyScore( shelfitems.get(i).getRelevancyScore());
            shelfitemDTOS.add(shelfitemDTO);
            i++;
        }
        shopperDTO.setShelf(shelfitemDTOS);
        return shopperDTO;
    }

    @Override
    public ShelfitemInputDTO shelfitemtoShelfitemDTOConverter(Shelfitem shelfitem){
        ShelfitemInputDTO shelfitemDTO = new ShelfitemInputDTO();
        shelfitemDTO.setProductId( shelfitem.getProductId() );
        shelfitemDTO.setRelevancyScore(shelfitem.getRelevancyScore() );
        shelfitemDTO.setShopperId( shelfitem.getShopperId() );
        return shelfitemDTO;
    }

    @Override
    public Shopper shopperDTOtoShopperConverter(ShopperDTO shopperDTO){
        Shopper shopper = new Shopper();
        shopper.setShopperId(shopperDTO.getShopperId());
        return shopper;
    }

    @Override
    public List<Shelfitem> shopperDTOtoShelfitemConverter(ShopperDTO shopperDTO){
        //an empty arraylist
        List<Shelfitem> shelfitems= new ArrayList<>();

        int countShelves=shopperDTO.getShelf().size();
        int n = 0;
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


}
