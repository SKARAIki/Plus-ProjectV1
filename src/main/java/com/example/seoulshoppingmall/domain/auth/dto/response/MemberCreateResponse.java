package com.example.seoulshoppingmall.domain.auth.dto.response;

import java.time.LocalDateTime;

public class MemberCreateResponse {
    //속성
    private Long id;

    //생성자
    public MemberCreateResponse(Long id) {
        this.id = id;
    }

    //기능
    public Long getId() {
        return id;
    }
}
