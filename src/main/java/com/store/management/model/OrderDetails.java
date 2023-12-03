package com.store.management.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
 
@Data
public class OrderDetails {
	
    
    private Integer userId;
    
    @JsonAlias("orderItem")
    private List<Items> items;
    
    private String shippingAddress;
    private Integer paymentActionId;
  
 
}