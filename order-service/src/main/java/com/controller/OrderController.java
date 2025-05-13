package com.example.order_service.controller;

import com.example.order_service.client.ProductClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final ProductClient productClient;

    public OrderController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/order/{id}")
    public String placeOrder(@PathVariable Long id) {
        String product = productClient.getProductById(id);
        return "Commande passée pour le produit : " + product;
    }
}
