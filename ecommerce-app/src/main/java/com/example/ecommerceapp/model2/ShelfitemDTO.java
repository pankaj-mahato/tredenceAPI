package com.example.ecommerceapp.model2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfitemDTO {
    private String productId;
    private long relevancyScore;
}
