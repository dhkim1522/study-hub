package com.springbasicapiserver.member.dto;

import com.springbasicapiserver.member.domain.vo.Age;
import com.springbasicapiserver.member.domain.vo.PhoneNumber;

public record MemberResponse(
        String name,
        Age age,
        PhoneNumber phoneNumber
) {
}
