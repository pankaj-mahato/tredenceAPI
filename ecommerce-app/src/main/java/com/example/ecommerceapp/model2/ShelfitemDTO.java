package com.example.ecommerceapp.model2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfitemDTO {
    private String shopperId;
    private String productId;
    private long relevancyScore;
}
