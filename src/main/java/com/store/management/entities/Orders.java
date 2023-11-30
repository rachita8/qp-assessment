package com.store.management.entities;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;
 
@Entity
@Data
@Table(name = "orders", schema = "store_management")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name = "total_quantity")
    private Integer totalQuantity;
    
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    
    @Column(name = "shipping_address")
    private String shippingAddress;
    
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products product;
 
    @JoinColumn(name = "payment_action_id", referencedColumnName = "id")
    private PaymentAction paymentAction;
 
 
 
 

}