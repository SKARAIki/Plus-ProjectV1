package com.example.seoulshoppingmall.domain.auth.dto.response;

public class LoginResponse {
    //속성
    private final TokenResponse data;

    //생성자
    public LoginResponse(TokenResponse data) {
        this.data = data;
    }

    //기능
    public TokenResponse getData() {
        return data;
    }
}
