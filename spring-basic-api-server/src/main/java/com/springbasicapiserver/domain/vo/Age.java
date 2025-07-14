package com.springbasicapiserver.domain.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Age(@JsonValue int age) {
    @JsonCreator
    public Age {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
    }
}
