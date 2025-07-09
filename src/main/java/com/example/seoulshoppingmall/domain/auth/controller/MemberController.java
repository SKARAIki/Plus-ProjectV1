package com.example.seoulshoppingmall.domain.auth.controller;

import com.example.seoulshoppingmall.domain.auth.dto.request.LoginRequest;
import com.example.seoulshoppingmall.domain.auth.dto.request.MemberCreateRequest;
import com.example.seoulshoppingmall.domain.auth.dto.response.LoginResponse;
import com.example.seoulshoppingmall.domain.auth.dto.response.MemberCreateResponse;
import com.example.seoulshoppingmall.domain.auth.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    //속성
    private final MemberService memberService;

    //생성자
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    //기능
    /**
     * 회원 가입 API
     */
    @PostMapping
    public ResponseEntity<MemberCreateResponse> createMemberAPI(@RequestBody MemberCreateRequest requestDto) {
        MemberCreateResponse responseDto = memberService.createMember(requestDto);
        ResponseEntity<MemberCreateResponse> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        return response;
    }

    /**
     * 로그인 API
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginAPI(@RequestBody LoginRequest requestDto) {
        LoginResponse responseDto = memberService.login(requestDto);
        ResponseEntity<LoginResponse> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

}
