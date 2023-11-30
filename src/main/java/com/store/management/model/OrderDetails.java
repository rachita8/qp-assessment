package com.store.management.model;


import java.util.List;

import lombok.Data;
 
@Data
public class OrderDetails {
	
    
    private Integer userId;
    private List<Items> items;
    private String shippingAddress;
    private Integer paymentActionId;
  
 
}