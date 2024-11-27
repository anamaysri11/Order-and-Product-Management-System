package com.example.orderManagementApplication.order.management.controller;

import com.example.orderManagementApplication.order.management.entity.Orders;
import com.example.orderManagementApplication.order.management.model.OrderDetails;
import com.example.orderManagementApplication.order.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderDetails order) {
        Orders newOrder = orderService.createOrder(order);
        if (newOrder == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(newOrder);
        }
    }

    @GetMapping("/retrieve-orders")
    public List<Orders> retrieveAllOrders() {
        return orderService.retrieveAllOrders();
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Orders> retrieveOrder(@PathVariable Long id) {
        Orders order = orderService.retrieveOrder(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(order);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable Long id) {
        boolean deletedOrder = orderService.deleteOrder(id);
        if (deletedOrder) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
