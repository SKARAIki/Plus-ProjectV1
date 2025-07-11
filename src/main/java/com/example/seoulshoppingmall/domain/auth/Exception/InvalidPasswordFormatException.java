package com.example.seoulshoppingmall.domain.auth.Exception;

public class InvalidPasswordFormatException extends RuntimeException{
    public InvalidPasswordFormatException() {
        super("비밀번호는 대소문자, 숫자, 특수문자를 포함해야 합니다.");
    }

    //생성자
    public InvalidPasswordFormatException(String message) {
        super(message);
    }
}
