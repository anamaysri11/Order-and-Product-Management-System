package com.example.orderManagementApplication.order.management.dao;

import com.example.orderManagementApplication.order.management.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {
}
