package com.example.orderManagementApplication.order.management.service;

import com.example.orderManagementApplication.order.management.dao.CustomerRepository;
import com.example.orderManagementApplication.order.management.dao.OrderRepository;
import com.example.orderManagementApplication.order.management.entity.Orders;
import com.example.orderManagementApplication.order.management.model.OrderDetails;
import com.example.orderManagementApplication.order.management.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private RestTemplate restTemplate;

    public boolean deleteOrder(Long id) {
        boolean orderExists = orderRepository.existsById(id);
        if (orderExists) {
            orderRepository.deleteById(id);
        }
        return orderExists;
    }
    public Orders createOrder(OrderDetails orderDetails) {
        if (orderDetails.getModeOfPayment() == null) return null;
        if (customerRepository.findById(orderDetails.getCustomerId()).orElse(null) == null) return null;

        Orders order = new Orders();
        order.setCustomerId(orderDetails.getCustomerId());
        order.setModeOfPayment(orderDetails.getModeOfPayment());
        double totalAmt = totalAmount(orderDetails);
        String selectedProducts = selectedProd(orderDetails);
        order.setTotalAmount(totalAmt);
        order.setSelectedProductsMap(selectedProducts);
        orderRepository.save(order);
        return order;
    }

    private String selectedProd(OrderDetails orderDetails) {
        String url = "http://localhost:8081/products/retrieve/";
        String selectedProducts = "";
        Iterator<Map.Entry<Long, Integer>> itr = orderDetails.getSelectedProductsMap().entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Long, Integer> productItem = itr.next();
            Product response = restTemplate.getForObject(url + productItem.getKey(), Product.class);
            selectedProducts += productItem.getKey();
            if (itr.hasNext()) selectedProducts += ",";
        }
        return selectedProducts;
    }

    private double totalAmount(OrderDetails orderDetails) {
        String url = "http://localhost:8081/products/retrieve/";
        double totalAmount = 0;
        Iterator<Map.Entry<Long, Integer>> itr = orderDetails.getSelectedProductsMap().entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Long, Integer> productItem = itr.next();
            Product response = restTemplate.getForObject(url + productItem.getKey(), Product.class);
            totalAmount += productItem.getValue() * response.getProductCost();
            restTemplate.getForEntity(url + productItem.getKey() + "/decrement-stock/" + productItem.getValue(), Product.class);
        }
        return totalAmount;
    }

    public List<Orders> retrieveAllOrders() {
        return orderRepository.findAll();
    }

    public Orders retrieveOrder(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}

