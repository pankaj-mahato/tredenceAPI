package com.example.ecommerceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopper")
public class Shopper {
    @Id
    @Column(name = "shopper_id")
    private String shopperId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopper_id", referencedColumnName = "shopper_id")
    private List<Shelfitem> shelf;
}