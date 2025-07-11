package com.example.seoulshoppingmall.domain.mall.exception;

public class OpenApiException extends RuntimeException {
    public OpenApiException(String message) {
        super(message);
    }
}