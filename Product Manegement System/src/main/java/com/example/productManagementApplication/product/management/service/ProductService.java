package com.example.productManagementApplication.product.management.service;

import com.example.productManagementApplication.product.management.dao.ProductRepository;
import com.example.productManagementApplication.product.management.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Adding data
    public Product addProduct(Product product) {
        if (product.getProductName() == null)
            return null;
        if (product.getProductCost() <= 0.0)
            return null;
        if (product.getProductDescription() == null)
            return null;
        return productRepository.save(product);
    }

    // Retrieving data
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductsById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Updating data
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null)
            return null;
        if (product.getProductCost() > 0.0)
            existingProduct.setProductCost(product.getProductCost());
        if (product.getProductDescription() != null)
            existingProduct.setProductDescription(product.getProductDescription());
        if (product.getProductRemainingQty() != 0)
            existingProduct.setProductRemainingQty(product.getProductRemainingQty());
        if (product.getProductName() != null)
            existingProduct.setProductName(product.getProductName());
        return productRepository.save(existingProduct);
    }

    public boolean deleteProduct(Long id) {
        boolean productExists = productRepository.existsById(id);
        if (productExists) {
            productRepository.deleteById(id);
        }
        return productExists;
    }
    public boolean decreaseProductStock(Long id, int qty) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null)
            return false;
        boolean response = product.decreaseProductStock(qty);
        if (!response)
            return false;
        productRepository.save(product);
        return true;
    }

}
