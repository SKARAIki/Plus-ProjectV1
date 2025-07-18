package com.example.seoulshoppingmall.domain.auth.controller;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.domain.auth.dto.request.LoginRequest;
import com.example.seoulshoppingmall.domain.auth.dto.request.MemberCreateRequest;
import com.example.seoulshoppingmall.domain.auth.dto.response.MemberCreateResponse;
import com.example.seoulshoppingmall.domain.auth.dto.response.LoginTokenResponse;
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
    public ResponseEntity<ApiResponse<MemberCreateResponse>> createMemberAPI(@RequestBody MemberCreateRequest request) {
        MemberCreateResponse memberData = memberService.createMember(request);
        ResponseEntity<ApiResponse<MemberCreateResponse>> response = ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED, "회원가입이 완료되었습니다", memberData));
        return response;
    }

    /**
     * 로그인 API
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginTokenResponse>> loginAPI(@RequestBody LoginRequest request) {
        LoginTokenResponse loginResponse = memberService.login(request);
        ResponseEntity<ApiResponse<LoginTokenResponse>> response = ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK, "로그인 성공", loginResponse)
        );
        return response;
    }

}
