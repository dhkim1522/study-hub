package com.springbasicapiserver.product.presentation.controller;

import com.springbasicapiserver.product.application.ProductService;
import com.springbasicapiserver.product.domain.Product;
import com.springbasicapiserver.product.persisetence.repository.ProductRepository;
import com.springbasicapiserver.product.presentation.dto.ProductRequest;
import com.springbasicapiserver.product.presentation.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        Product saved = productService.createProduct(request);
        return ResponseEntity.ok(ProductResponse.from(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductResponse.from(product));
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(ProductResponse::from)
                .toList();
    }
}
