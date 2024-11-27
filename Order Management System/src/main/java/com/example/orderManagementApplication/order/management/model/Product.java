package com.example.orderManagementApplication.order.management.model;

public class Product {
    private Long productId;
    private String productName;
    private double productCost;
    private int productRemainingQty;
    private String productDescription;

    public Product(String productName, double productCost, int productRemainingQty, String productDescription) {
        this.productName = productName;
        this.productCost = productCost;
        this.productRemainingQty = productRemainingQty;
        this.productDescription = productDescription;
    }

    public Long getProductId() { return productId; }

    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public double getProductCost() { return productCost; }

    public void setProductCost(double productCost) { this.productCost = productCost; }

    public int getProductRemainingQty() { return productRemainingQty; }

    public void setProductRemainingQty(int productRemainingQty) { this.productRemainingQty = productRemainingQty; }

    public String getProductDescription() { return productDescription; }

    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }
}
