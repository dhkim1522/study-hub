package com.springbasicapiserver.service;

import com.springbasicapiserver.domain.Member;
import com.springbasicapiserver.domain.vo.PhoneNumber;
import com.springbasicapiserver.dto.MemberRequest;
import com.springbasicapiserver.dto.MemberResponse;
import com.springbasicapiserver.mapper.MemberMapper;
import com.springbasicapiserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse register(MemberRequest request) {
        Member member = memberRepository.save(MemberMapper.toEntity(request));
        return MemberMapper.toResponse(member);
    }

    public List<MemberResponse> findAll() {
        return MemberMapper.toResponseList(memberRepository.findAll());
    }

    public MemberResponse findByPhoneNumber(PhoneNumber phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 전화번호를 가진 회원이 없습니다: " + phoneNumber.phoneNumber()));
        return MemberMapper.toResponse(member);
    }
}
