package com.example.orderManagementApplication.order.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetails {
    private Long orderId;
    @JsonProperty("selectedProducts")
    private Map<Long, Integer> selectedProductsMap;
    @JsonProperty("customerId")
    private Long customerId;
    private double totalAmt;
    @JsonProperty("modeOfPayments")
    private String modeOfPayment;

    public OrderDetails(Long orderId, Map<Long, Integer> selectedProductsMap, Long customerId, double totalAmt, String modeOfPayment) {
        this.orderId = orderId;
        this.selectedProductsMap = selectedProductsMap;
        this.customerId = customerId;
        this.totalAmt = totalAmt;
        this.modeOfPayment = modeOfPayment;
    }

    public Long getOrderId() { return orderId; }

    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Map<Long, Integer> getSelectedProductsMap() { return selectedProductsMap; }

    public void setSelectedProductsMap(Map<Long, Integer> selectedProductsMap) {
        this.selectedProductsMap = selectedProductsMap;
    }
    public Long getCustomerId() { return customerId; }

    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public double getTotalAmt() { return totalAmt; }

    public void setTotalAmt(double totalAmt) { this.totalAmt = totalAmt; }

    public String getModeOfPayment() { return modeOfPayment; }

    public void setModeOfPayment(String modeOfPayment) { this.modeOfPayment = modeOfPayment; }
}

