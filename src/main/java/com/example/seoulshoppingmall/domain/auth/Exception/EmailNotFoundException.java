package com.example.seoulshoppingmall.domain.auth.Exception;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException() {
        super("존재하지 않는 이메일입니다.");
    }

    //생성자
    public EmailNotFoundException(String message) {
        super(message);
    }
}
