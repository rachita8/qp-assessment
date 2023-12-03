package com.store.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.management.entities.Products;
import com.store.management.model.OrderDetails;
import com.store.management.model.ProductDetails;
import com.store.management.service.DataServices;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private DataServices dataServices;
	
	
	
		// Ability to book multiple grocery items in a single order
		@PostMapping("/orders-item")
		public String saveAdmin(@RequestBody OrderDetails orderDetails) 
		{
			String response = dataServices.saveOrderDetail(orderDetails);
			
			return response;
		}
		
		//View the list of available grocery items
		@GetMapping("/grocery-items")
		public List<Products> getAvailableProductItems()
		{
			return dataServices.getAvailableProduct();
		}
		
}