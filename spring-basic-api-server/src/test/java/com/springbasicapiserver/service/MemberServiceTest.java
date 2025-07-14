package com.springbasicapiserver.service;

import com.springbasicapiserver.domain.Member;
import com.springbasicapiserver.domain.vo.Age;
import com.springbasicapiserver.domain.vo.PhoneNumber;
import com.springbasicapiserver.dto.MemberRequest;
import com.springbasicapiserver.dto.MemberResponse;
import com.springbasicapiserver.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    public MemberServiceTest() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
    }

    @Test
    void register_회원등록_성공() {
        // given
        MemberRequest request = new MemberRequest(
                "홍길동",
                new Age(25),
                new PhoneNumber("010-1234-5678")
        );

        Member saved = Member.builder()
                .name(request.name())
                .age(request.age())
                .phoneNumber(request.phoneNumber())
                .build();

        when(memberRepository.save(any(Member.class))).thenReturn(saved);

        // when
        MemberResponse res = memberService.register(request);

        // then
        assertThat(res.name()).isEqualTo("홍길동");
        assertThat(res.age()).isEqualTo(new Age(25));
        assertThat(res.phoneNumber()).isEqualTo(new PhoneNumber("010-1234-5678"));

        verify(memberRepository, times(1)).save(any(Member.class));
    }
}