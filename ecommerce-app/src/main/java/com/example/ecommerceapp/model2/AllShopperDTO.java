package com.example.ecommerceapp.model2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllShopperDTO {
    private List<ShopperDTO> multipleShopperData;
}
