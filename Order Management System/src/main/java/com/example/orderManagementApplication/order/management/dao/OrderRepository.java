package com.example.orderManagementApplication.order.management.dao;

import com.example.orderManagementApplication.order.management.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends
        JpaRepository<Orders, Long> {
}
