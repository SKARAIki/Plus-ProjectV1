package com.example.trendsixtown.global;

public class fileException extends RuntimeException {

    public fileException() {
        super("파일이 존재하지 않습니다");
    }

}
