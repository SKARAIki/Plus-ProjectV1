package com.example.seoulshoppingmall.common.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    //속성
    private final int status;
    private final String message;
    private final T data;

    //생성자
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    //기능
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
