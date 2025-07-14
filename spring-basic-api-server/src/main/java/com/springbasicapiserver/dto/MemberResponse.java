package com.springbasicapiserver.dto;

import com.springbasicapiserver.domain.vo.Age;
import com.springbasicapiserver.domain.vo.PhoneNumber;

public record MemberResponse(
        String name,
        Age age,
        PhoneNumber phoneNumber
) {
}
