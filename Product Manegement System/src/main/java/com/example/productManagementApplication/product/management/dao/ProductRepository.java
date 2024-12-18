package com.example.productManagementApplication.product.management.dao;

import com.example.productManagementApplication.product.management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
