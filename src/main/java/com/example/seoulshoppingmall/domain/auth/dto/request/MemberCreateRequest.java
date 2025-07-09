package com.example.seoulshoppingmall.domain.auth.dto.request;

public class MemberCreateRequest {
    //속성
    private String email;
    private String memberName;
    private String password;

    //생성자

    //기능
    public String getEmail() {
        return email;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPassword() {
        return password;
    }
}
