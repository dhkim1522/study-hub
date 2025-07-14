package com.springbasicapiserver.product.application;

import com.springbasicapiserver.product.domain.Product;
import com.springbasicapiserver.product.persisetence.repository.ProductRepository;
import com.springbasicapiserver.product.presentation.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest request) {
        Product product = request.toEntity();
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
