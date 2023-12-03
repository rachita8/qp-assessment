package com.store.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.entities.Products;
 
public interface ProductsRepository extends JpaRepository<Products, Integer> {
 
	List<Products> findByQuantityGreaterThan(Integer quantity);
}