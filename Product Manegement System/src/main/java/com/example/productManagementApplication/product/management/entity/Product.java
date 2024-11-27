package com.example.productManagementApplication.product.management.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @Column(name = "PRODUCT_COST")
    private double productCost;

    @Column(name = "PRODUCT_STOCK")
    private int productRemainingQty;

    public Product() {}

    public Product(String productName, String productDescription, double productCost, int productRemainingQty) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCost = productCost;
        this.productRemainingQty = productRemainingQty;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public int getProductRemainingQty() {
        return productRemainingQty;
    }

    public void setProductRemainingQty(int productRemainingQty) {
        this.productRemainingQty = productRemainingQty;
    }

    public boolean decreaseProductStock(int qty) {
        if (this.productRemainingQty < qty) {
            return false;
        }
        this.productRemainingQty -= qty;
        return true;
    }

}
