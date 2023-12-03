package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.entities.OrderItem;
 
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
 
}