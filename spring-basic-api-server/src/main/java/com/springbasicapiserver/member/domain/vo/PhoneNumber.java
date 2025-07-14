package com.springbasicapiserver.member.domain.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(@JsonValue String phoneNumber) {
    @JsonCreator
    public PhoneNumber {
        if (!phoneNumber.matches( "^010-\\d{4}-\\d{4}$")) {
            throw new IllegalArgumentException("Invalid phone number: " + phoneNumber);
        }
    }
}
