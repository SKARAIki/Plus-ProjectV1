package com.example.seoulshoppingmall.domain.mall.exception;

public class InvalidQueryParameterException extends RuntimeException {
    public InvalidQueryParameterException(String message) {
        super(message);
    }
}
