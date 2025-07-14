package com.springbasicapiserver.product.persisetence.repository;

import com.springbasicapiserver.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
