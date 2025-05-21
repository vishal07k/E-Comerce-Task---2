package com.ecomerce.ecomerce.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private int qty;
    
    private String description;
    
    @ManyToMany
    private List<Category> category;
    
    @ManyToMany(mappedBy = "product")
    private List<Order> orders;
}
