package com.springbasicapiserver.repository;

import com.springbasicapiserver.domain.Member;
import com.springbasicapiserver.domain.vo.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByPhoneNumber(PhoneNumber phoneNumber);
}
