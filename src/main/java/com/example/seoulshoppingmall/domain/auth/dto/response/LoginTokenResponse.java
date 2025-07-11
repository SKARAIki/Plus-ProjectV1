package com.example.seoulshoppingmall.domain.auth.dto.response;

public class TokenResponse {
    //속성
    private final String token;

    //생성자
    public TokenResponse(String token) {
        this.token = token;
    }

    //기능
    public String getToken() {
        return token;
    }
}
