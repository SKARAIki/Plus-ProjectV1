package com.example.seoulshoppingmall.domain.mall.dto.response;

public class MallCreateResponse {
    // 속성
    private int status;
    private String message;

    // 생성자 , 기본생성자 생략
    public MallCreateResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getter Setter
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
