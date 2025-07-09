package com.example.seoulshoppingmall.common.exception;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.domain.mall.exception.NotFoundParamException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundParamException.class)
    public ApiResponse<Void> handleNotFoundOverallRatingException() {
        return ApiResponse.error(HttpStatus.BAD_REQUEST, "필터 항목이 비어 있습니다");
    }
}
