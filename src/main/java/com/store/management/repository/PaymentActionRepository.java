package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.entities.Orders;
import com.store.management.entities.PaymentAction;
 
public interface PaymentActionRepository extends JpaRepository<PaymentAction, Integer> {
 
}