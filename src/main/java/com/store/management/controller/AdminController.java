package com.store.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.store.management.exception.ResourceNotFoundException;
import com.store.management.model.ProductDetails;
import com.store.management.service.DataServices;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private DataServices dataServices;
	
	
	    //1.Add new grocery items
		@PostMapping("/grocery-item")  // add grocery items
		public String saveProduct(@RequestBody ProductDetails productDetails) 
		{
			String response = dataServices.saveProductDetail(productDetails);
			
			return response;
		}
		
		//2. View existing grocertItems
		@GetMapping("/grocery-items")
		public List<Products> getProductItems()
		{
			return dataServices.getAllProducts();
		}
		
		//3. Remove grocery items from the system
		@DeleteMapping("/grocery-item/{productId}")
		public String deleteProduct(@PathVariable(required = true) Integer productId) {
			
			String response = dataServices.deleteProduct(productId);
			
			return response;
		}
		
		//4.Update details of existing grocery items
		@PutMapping("/grocery-item/{productId}")
		public String updateProduct(@PathVariable(required = true) Integer productId,@RequestBody ProductDetails productDetails) {
			
			String response = dataServices.updateProduct(productId,productDetails);
			
			return response;
		}
		
		//Manage Inventory levels of grocery items
		@PatchMapping("/grocery-item/{productId}/inventory")
		public String updateInventory(@PathVariable(required = true) Integer productId,@RequestParam Integer quantity) {
			
			String response = dataServices.manageInventory(productId,quantity);
			
			return response;
		}


}
