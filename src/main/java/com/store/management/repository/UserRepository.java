package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.entities.User;
 
public interface UserRepository extends JpaRepository<User, Integer> {
 
}