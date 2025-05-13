package com.example.product_service.controller;

import com.example.product_service.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private Map<Long, Product> productMap = new HashMap<>();

    @PostConstruct
    public void setupProducts() {
        Product p1 = new Product(1L, "Laptop", "High performance laptop", 999.99);
        Product p2 = new Product(2L, "Phone", "Smartphone with great camera", 699.99);
        productMap.put(p1.getId(), p1);
        productMap.put(p2.getId(), p2);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productMap.get(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        if (productMap.containsKey(product.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        productMap.put(product.getId(), product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
