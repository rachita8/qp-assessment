package com.store.management.entities;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
 
@Entity
@Data
@Table(name = "order_item", schema = "store_management")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "price")
    private BigDecimal price;
      
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products product;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
 
 

}