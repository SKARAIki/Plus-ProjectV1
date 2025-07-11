package com.example.seoulshoppingmall.domain.auth.Exception;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException() {
        super("이미 존재하는 이메일입니다.");
    }

    //생성자
    public DuplicateEmailException(String message) {
        super(message);
    }
}
