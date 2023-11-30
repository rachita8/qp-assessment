package com.store.management.entities;

import javax.persistence.*;

import lombok.Data;
 
@Entity
@Data
@Table(name = "roles", schema = "store_management")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name = "name")
    private String name;
    
}