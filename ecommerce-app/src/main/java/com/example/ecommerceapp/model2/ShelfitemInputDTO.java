package com.example.ecommerceapp.model2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfitemInputDTO {
    private String shopperId;
    private String productId;
    private long relevancyScore;
}
