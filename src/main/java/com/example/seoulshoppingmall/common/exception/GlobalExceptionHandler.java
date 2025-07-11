package com.example.seoulshoppingmall.common.exception;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.domain.mall.exception.NotFoundParamException;
import com.example.seoulshoppingmall.domain.mall.exception.InvalidQueryParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.seoulshoppingmall.common.dto.ErrorResponse;
import com.example.seoulshoppingmall.domain.mall.exception.OpenApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundParamException.class)
    public ApiResponse<Void> handleNotFoundOverallRatingException() {
        return ApiResponse.error(HttpStatus.BAD_REQUEST, "필터 항목이 비어 있습니다");
    }
        @ExceptionHandler(InvalidQueryParameterException.class)
        public ResponseEntity<ApiResponse<?>> handleInvalidQuery (InvalidQueryParameterException ex){
            ApiResponse<?> response = ApiResponse.error(HttpStatus.BAD_REQUEST, ex.getMessage());
            return ResponseEntity.badRequest().body(response);

        }
    // 예를 들어 외부 OpenAPI 호출 중 오류가 발생한 경우
    @ExceptionHandler(OpenApiException.class)
    public ResponseEntity<ErrorResponse> handleOpenApiCallException(OpenApiException openApiException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, openApiException.getMessage()));
    }

    // 예상하지 못한 예외가 발생한 경우에도 사용자에게 500 에러로 응답 -> 메시지로 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "서버 오류가 발생했습니다."));
    }
}