package com.example.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Le nom doit correspondre à celui défini dans Eureka ou application.properties du service produit
@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    String getProductById(@PathVariable("id") Long id);
}
