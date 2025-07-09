package com.example.seoulshoppingmall.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    //속성
    @NotBlank(message = "null/공백은 입력 불가합니다.")
    @Email(message = "email 형식을 입력하세요.")
    private String email;

    @NotBlank(message = "null/공백은 입력 불가합니다.")
    private String password;

    //생성자
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //기능
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
