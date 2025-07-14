package com.springbasicapiserver.member.domain;

import com.springbasicapiserver.member.domain.vo.Age;
import com.springbasicapiserver.member.domain.vo.PhoneNumber;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Age age;

    @Embedded
    private PhoneNumber phoneNumber;

    @Builder
    public Member(String name, Age age, PhoneNumber phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
}
