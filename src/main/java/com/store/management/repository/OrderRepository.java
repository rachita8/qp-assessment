package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.entities.Orders;
 
public interface OrderRepository extends JpaRepository<Orders, Integer> {
 
}