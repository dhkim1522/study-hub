package com.springbasicapiserver.product.domain;

import java.util.Objects;

public record Money(long amount, CurrencyCode currency) {
    public Money {
        if (amount < 0) throw new IllegalArgumentException("Amount must be non-negative");
        Objects.requireNonNull(currency, "Currency must not be null");
    }
}