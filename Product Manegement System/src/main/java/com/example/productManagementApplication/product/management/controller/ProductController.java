
package com.example.productManagementApplication.product.management.controller;
import com.example.productManagementApplication.product.management.entity.Product;
import com.example.productManagementApplication.product.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/retrieve")
    public List<Product> retrieveProducts() {
        List<Product> products = productService.getAllProduct();
        return products;
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long id) {
        Product product = productService.getProductsById(id);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        if(newProduct == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(newProduct);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if(updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(updatedProduct);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        boolean deletedProduct = productService.deleteProduct(id);
        if(deletedProduct) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/decrement-stock/{qty}")
    public ResponseEntity<String> decrementStock(@PathVariable Long id, @PathVariable int qty) {
        boolean stockDecreased = productService.decreaseProductStock(id, qty);
        if(stockDecreased) {
            return ResponseEntity.ok().body("Stock decreased successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to decrease stock");
        }
    }

}
