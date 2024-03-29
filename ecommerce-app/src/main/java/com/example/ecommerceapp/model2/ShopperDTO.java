package com.example.ecommerceapp.model2;

import com.example.ecommerceapp.model.Shelfitem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopperDTO {
    private String shopperId;
    private List<Shelfitem> items;
}
