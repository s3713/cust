package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    public double deposit(Long customerId, double amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // Implement deposit logic
        // Example: Update customer balance
        double newBalance = customer.getBalance() + amount;
        customer.setBalance(newBalance);
        customerRepository.save(customer);
        return newBalance;
    }

    public double withdraw(Long customerId, double amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // Implement withdrawal logic
        // Example: Check if sufficient balance
        if (customer.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        double newBalance = customer.getBalance() - amount;
        customer.setBalance(newBalance);
        customerRepository.save(customer);
        return newBalance;
    }

    public void updateProfile(Long customerId, String fullName, String email) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // Implement update profile logic
        if (fullName != null && !fullName.isEmpty()) {
            customer.setFullName(fullName);
        }
        if (email != null && !email.isEmpty()) {
            customer.setEmail(email);
        }
        customerRepository.save(customer);
    }

    public double checkBalance(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // Implement check balance logic
        return customer.getBalance();
    }

    public void changePassword(Long customerId, String newPassword) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // Implement change password logic
        // Example: Update customer password
        customer.setPassword(newPassword);
        customerRepository.save(customer);
    }
}