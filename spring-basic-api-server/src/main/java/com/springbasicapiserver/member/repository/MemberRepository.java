package com.springbasicapiserver.member.repository;

import com.springbasicapiserver.member.domain.Member;
import com.springbasicapiserver.member.domain.vo.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByPhoneNumber(PhoneNumber phoneNumber);
}
