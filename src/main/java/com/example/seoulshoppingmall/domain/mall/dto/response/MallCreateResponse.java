package com.example.seoulshoppingmall.domain.mall.dto.response;

public class MallCreateResponse {
    // 속성
    private int status;
    private String message;

    // 기본 생성자
    public MallCreateResponse() {
    }

    // 생성자
    public MallCreateResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getter Setter
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
