package com.example.seoulshoppingmall.domain.auth.Exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }

    //생성자
    public InvalidPasswordException(String message) {
        super(message);
    }
}
