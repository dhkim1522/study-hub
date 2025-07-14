package com.springbasicapiserver.product.persisetence.converter;

import com.springbasicapiserver.product.domain.CurrencyCode;
import com.springbasicapiserver.product.domain.Money;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class MoneyConverter implements AttributeConverter<Money, String> {

    @Override
    public String convertToDatabaseColumn(Money money) {
        if (money == null) return null;
        return money.amount() + ":" + money.currency().name();
    }

    @Override
    public Money convertToEntityAttribute(String dbData) {
        if (dbData == null || !dbData.contains(":")) return null;
        String[] parts = dbData.split(":");
        long amount = Long.parseLong(parts[0]);
        CurrencyCode currency = CurrencyCode.valueOf(parts[1]);
        return new Money(amount, currency);
    }
}
