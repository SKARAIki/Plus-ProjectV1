package com.example.seoulshoppingmall.domain.auth.dto.response;

public class LoginTokenResponse {
    //속성
    private final String JwtToken;

    //생성자
    public LoginTokenResponse(String jwtToken) {
        this.JwtToken = jwtToken;
    }

    //기능
    public String getJwtToken() {
        return JwtToken;
    }
}
