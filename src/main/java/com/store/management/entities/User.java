package com.store.management.entities;


import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;
 
@Entity
@Data
@Table(name = "user", schema = "store_management")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name = "username")
    private String username;
    
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles role;
 
    // getters and setters...
}