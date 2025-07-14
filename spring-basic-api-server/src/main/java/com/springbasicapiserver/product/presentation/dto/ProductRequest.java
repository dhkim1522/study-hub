package com.springbasicapiserver.product.presentation.dto;

import com.springbasicapiserver.product.domain.CurrencyCode;
import com.springbasicapiserver.product.domain.Money;
import com.springbasicapiserver.product.domain.Product;

public record ProductRequest(
        String name,
        long amount,
        CurrencyCode currency
) {
    public Product toEntity() {
        return new Product(name, new Money(amount, currency));
    }
}
