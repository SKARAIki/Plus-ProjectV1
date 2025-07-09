package com.example.seoulshoppingmall.domain.auth.dto.response;

public class LoginResponse {
    //속성
    private final String token;

    //생성자
    public LoginResponse(String token) {
        this.token = token;
    }

    //기능
    public String getToken() {
        return token;
    }
}
