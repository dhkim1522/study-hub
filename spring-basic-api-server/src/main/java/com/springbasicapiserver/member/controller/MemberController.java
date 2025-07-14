package com.springbasicapiserver.member.controller;

import com.springbasicapiserver.member.domain.vo.PhoneNumber;
import com.springbasicapiserver.member.dto.MemberRequest;
import com.springbasicapiserver.member.dto.MemberResponse;
import com.springbasicapiserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> register(@RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberService.register(request));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping(params = "phoneNumber")
    public ResponseEntity<MemberResponse> findByPhoneNumber(@RequestParam PhoneNumber phoneNumber) {
        var member = memberService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(member);
    }
}
