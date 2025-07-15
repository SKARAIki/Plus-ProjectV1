package com.example.seoulshoppingmall.domain.auth.Exception;

public class FileException extends RuntimeException {

    public FileException() {
        super("CSV 업로드에 실패하였습니다.");
    }

}
