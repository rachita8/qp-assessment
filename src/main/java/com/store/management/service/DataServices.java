package com.store.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.management.entities.Products;
import com.store.management.model.OrderDetails;
import com.store.management.model.ProductDetails;

@Service
public interface DataServices {
	
	//Admin
	
	public List<Products> getAllProducts();
	
	public String saveProductDetail(ProductDetails productDetails);
	
	public String deleteProduct(Integer productId);
	
	public String updateProduct(Integer productId,ProductDetails productDetails);

	public String manageInventory(Integer productId,Integer quantity);
	
	//User
	
	public List<Products> getAvailableProduct();
	public String saveOrderDetail(OrderDetails orderDetails);
}
