package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customers")
public class CustomerController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public void addCustomer(@RequestBody Customer customer) {
    	
        customerService.addCustomer(customer);
    }

    @DeleteMapping("/delete/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/list")
    public Iterable<Customer> listCustomers() {
    	LOG.info("inside customers");
        return customerService.listCustomers();
    }

    @PostMapping("/deposit")
    public double deposit(@RequestParam Long customerId, @RequestParam double amount) {
        return customerService.deposit(customerId, amount);
    }

    @PostMapping("/withdraw")
    public double withdraw(@RequestParam Long customerId, @RequestParam double amount) {
        return customerService.withdraw(customerId, amount);
    }

    @PostMapping("/updateProfile")
    public void updateProfile(@RequestParam Long customerId, @RequestParam(required = false) String fullName, @RequestParam(required = false) String email) {
        customerService.updateProfile(customerId, fullName, email);
    }

    @GetMapping("/checkBalance")
    public double checkBalance(@RequestParam Long customerId) {
        return customerService.checkBalance(customerId);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestParam Long customerId, @RequestParam String newPassword) {
        customerService.changePassword(customerId, newPassword);
    }
}