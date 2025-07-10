package com.example.seoulshoppingmall.domain.auth.dto.response;

import java.time.LocalDateTime;

public class MemberCreateResponse {
    //속성
    private int status;
    private String message;
    private Long id;

    //생성자
    public MemberCreateResponse(int status, String message, Long id) {
        this.status = status;
        this.message = message;
        this.id = id;
    }

    //기능
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }
}
