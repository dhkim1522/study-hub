package com.springbasicapiserver.member.mapper;

import com.springbasicapiserver.member.domain.Member;
import com.springbasicapiserver.member.dto.MemberRequest;
import com.springbasicapiserver.member.dto.MemberResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {
    public static Member toEntity(MemberRequest request) {
        return Member.builder()
                .name(request.name())
                .age(request.age())
                .phoneNumber(request.phoneNumber())
                .build();
    }

    public static MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getName(),
                member.getAge(),
                member.getPhoneNumber()
        );
    }

    public static List<MemberResponse> toResponseList(List<Member> members) {
        return members.stream()
                .map(MemberMapper::toResponse)
                .toList();
    }
}
