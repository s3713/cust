package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    
}