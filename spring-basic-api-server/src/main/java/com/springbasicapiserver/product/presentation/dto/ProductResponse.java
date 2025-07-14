package com.springbasicapiserver.product.presentation.dto;

import com.springbasicapiserver.product.domain.CurrencyCode;
import com.springbasicapiserver.product.domain.Product;

public record ProductResponse(
        String name,
        long amount,
        CurrencyCode currency
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getName(),
                product.getPrice().amount(),
                product.getPrice().currency()
        );
    }
}
