package com.example.orderManagementApplication.order.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="ORDERS")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long orderId;

    @Column(name = "SELECTED_PRODUCTS")
    private String selectedProductsMap;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "AMOUNT")
    private double totalAmount;

    @Column(name = "PAYMENT_MODE")
    private String modeOfPayment;

    public Orders(String selectedProductsMap, Long customerId, double totalAmount, String modeOfPayment) {
        this.selectedProductsMap = selectedProductsMap;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.modeOfPayment = modeOfPayment;
    }

    public Orders() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getSelectedProductsMap() {
        return selectedProductsMap;
    }

    public void setSelectedProductsMap(String selectedProductsMap) {
        this.selectedProductsMap = selectedProductsMap;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }
}
