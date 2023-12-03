package com.store.management.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    
    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles role;
 
    // getters and setters...
}