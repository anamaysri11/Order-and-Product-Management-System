package com.example.orderManagementApplication.order.management.controller;

import com.example.orderManagementApplication.order.management.entity.Customers;
import com.example.orderManagementApplication.order.management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<Customers> addCustomer(@RequestBody Customers customer) {
        Customers newCustomer = customerService.addCustomer(customer);
        if (newCustomer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(newCustomer);
        }
    }

    @GetMapping("/retrieve")
    public List<Customers> retrieveAllCustomer() {
        return customerService.allCustomers();
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Customers> retrieveCustomer(@PathVariable Long id) {
        Customers customer = customerService.retrieveCustomer(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(customer);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customers> deleteCustomer(@PathVariable Long id) {
        boolean deletedCustomer = customerService.deleteCustomer(id);
        if (deletedCustomer) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

