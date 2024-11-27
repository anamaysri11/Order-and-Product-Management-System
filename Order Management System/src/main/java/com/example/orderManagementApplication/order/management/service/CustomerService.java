package com.example.orderManagementApplication.order.management.service;

import com.example.orderManagementApplication.order.management.dao.CustomerRepository;
import com.example.orderManagementApplication.order.management.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customers addCustomer(Customers customer) {
        if (customer.getCustomerName() == null) return null;
        if (customer.getCustomerAddress() == null) return null;
        if (customer.getPhoneNo() == null || customer.getPhoneNo().length() < 10) return null;
        customerRepository.save(customer);
        return customer;
    }

    public boolean deleteCustomer(Long id) {
        boolean customerExists = customerRepository.existsById(id);
        if (customerExists) {
            customerRepository.deleteById(id);
        }
        return customerExists;
    }

    public List<Customers> allCustomers() {
        return customerRepository.findAll();
    }

    public Customers retrieveCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
}
