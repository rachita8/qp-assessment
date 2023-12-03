package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.entities.Roles;
 
public interface RolesRepository extends JpaRepository<Roles, Integer> {
 
}