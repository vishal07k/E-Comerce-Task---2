package com.ecomerce.ecomerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.ecomerce.ecomerce.enums.Role;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
    
    private String password;
    
    //@Enumerated(EnumType.STRING)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Order> order;
}