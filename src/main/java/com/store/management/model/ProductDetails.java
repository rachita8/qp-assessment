package com.store.management.model;


import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
 
@Data
public class ProductDetails {
	
    
    private String name;
    private BigDecimal price;
    private Integer quantity;
 
}