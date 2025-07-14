package com.springbasicapiserver.controller;

import com.springbasicapiserver.domain.Member;
import com.springbasicapiserver.domain.vo.PhoneNumber;
import com.springbasicapiserver.dto.MemberRequest;
import com.springbasicapiserver.dto.MemberResponse;
import com.springbasicapiserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.val;
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
