package com.example.orderManagementApplication.order.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="CUSTOMER")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long customerId;

    @Column(name="CUSTOMER_NAME")
    private String customerName;

    @Column(name="CUSTOMER_ADDRESS")
    private String customerAddress;

    @Column(name="PHONE_NO")
    private String phoneNo;

    public Customers(String customerName, String customerAddress, String phoneNo) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.phoneNo = phoneNo;
    }

    public Customers() {}
    public Long getCustomerId() { return customerId; }

    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() { return customerAddress; }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhoneNo() { return phoneNo; }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
