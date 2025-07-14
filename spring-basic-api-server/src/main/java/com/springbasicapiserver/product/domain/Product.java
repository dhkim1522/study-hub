package com.springbasicapiserver.product.domain;

import com.springbasicapiserver.product.persisetence.converter.MoneyConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    public Product(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
