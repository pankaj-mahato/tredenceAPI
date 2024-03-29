package com.example.ecommerceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shelf")
public class Shelfitem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopper_id", nullable = false)
    private String shopperId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "relevancy_score")
    private long relevancyScore;
}
