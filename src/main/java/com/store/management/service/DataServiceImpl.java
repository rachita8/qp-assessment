package com.store.management.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.management.entities.OrderItem;
import com.store.management.entities.Orders;
import com.store.management.entities.PaymentAction;
import com.store.management.entities.Products;
import com.store.management.entities.User;
import com.store.management.exception.NotExistException;
import com.store.management.model.Items;
import com.store.management.model.OrderDetails;
import com.store.management.model.ProductDetails;
import com.store.management.repository.OrderItemRepository;
import com.store.management.repository.OrderRepository;
import com.store.management.repository.PaymentActionRepository;
import com.store.management.repository.ProductsRepository;
import com.store.management.repository.RolesRepository;
import com.store.management.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataServiceImpl implements DataServices {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductsRepository productRepository;
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private PaymentActionRepository paymentActionRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public String saveProductDetail(ProductDetails productDetails) {
		
		try {
			
			Products product = new Products();
			setProductDetails(product, productDetails);		
			productRepository.save(product);
		}
		catch(Exception e) {
			return e.getLocalizedMessage();
		}
		return "Saved product successfully";
		
		
	}
	
	private void setProductDetails(Products product, ProductDetails productDetails) {
		product.setName(productDetails.getName());
		product.setPrice(productDetails.getPrice());
		product.setQuantity(productDetails.getQuantity());
	}

	@Override
	public List<Products> getAllProducts() {
		List<Products> products = productRepository.findAll();
		return products;
	}

	@Override
	public String deleteProduct(Integer productId) {
		
		try {
			productRepository.findById(productId).orElseThrow(NotExistException::new);
			productRepository.deleteById(productId);
		}
		catch(NotExistException e) {
			log.info(e.getLocalizedMessage());
			return e.getMessage();
		}
		
		return "Product deleted successfully";
	}

	@Override
	public String updateProduct(Integer productId, ProductDetails productDetails) {

		try {
			Products product=productRepository.findById(productId).orElseThrow(NotExistException::new);
			setProductDetails(product, productDetails);
			productRepository.save(product);
		}
		catch(NotExistException e) {
				log.info(e.getLocalizedMessage());
				return e.getMessage();
			}
		return "Product updated successfully";
	}

	@Override
	public String manageInventory(Integer productId, Integer quantity) {
		try {
			Products product=productRepository.findById(productId).orElseThrow(NotExistException::new);
			product.setQuantity(quantity);
			productRepository.save(product);
		}
		catch(NotExistException e) {
				log.info(e.getLocalizedMessage());
				return e.getMessage();
			}
		return "Quantity of product id: "+productId+" updated successfully.";
	}

	@Override
	public List<Products> getAvailableProduct() {
		List<Products> list = productRepository.findByQuantityGreaterThan(0);
		return list;
	}

	@Override
	public String saveOrderDetail(OrderDetails orderDetails) {
		BigDecimal totalAmount = BigDecimal.ZERO;
		try {
			User user=userRepository.findById(orderDetails.getUserId()).orElseThrow(NotExistException::new);
			Orders order = new Orders();
			
			PaymentAction paymentAction=paymentActionRepository.findById(orderDetails.getPaymentActionId()).orElseThrow(NotExistException::new);
			order.setPaymentAction(paymentAction);
			
			order.setUser(user);
			order.setOrderDate(LocalDateTime.now());
			order.setShippingAddress(orderDetails.getShippingAddress());
			Orders orders = orderRepository.save(order);
			
			Integer quantity = 0;
			for(Items item: orderDetails.getItems() ) {
				Products product=productRepository.findById(item.getProductId()).orElseThrow(NotExistException::new);
				OrderItem orderItem = new OrderItem();
				orderItem.setOrders(orders);
				orderItem.setPrice(product.getPrice());
				orderItem.setQuantity(item.getQuantity());
				orderItem.setProduct(product);
				
				//re-evaluating the inventory
				product.setQuantity(product.getQuantity()-item.getQuantity());
				productRepository.save(product);
				
				//calculating total Amount and quantity
				 BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
				 totalAmount = totalAmount.add(itemTotal);
				 quantity = quantity + item.getQuantity();
				 
				 orderItemRepository.save(orderItem);
			}
			Orders sameOrder = orderRepository.findById(orders.getId()).orElseThrow(NotExistException::new);;
			sameOrder.setTotalQuantity(quantity);
			sameOrder.setTotalAmount(totalAmount);
			orderRepository.save(sameOrder);
		}
		catch(NotExistException e) {
				log.info(e.getLocalizedMessage());
				return e.getMessage();
			}
		return "Order booked succesfully. Total Amount: "+ totalAmount;
	}
	

}
