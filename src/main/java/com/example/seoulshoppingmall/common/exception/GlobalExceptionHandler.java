package com.example.seoulshoppingmall.common.exception;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.domain.mall.exception.NotFoundParamException;
import com.example.seoulshoppingmall.domain.mall.exception.InvalidQueryParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.seoulshoppingmall.common.dto.ErrorResponse;
import com.example.seoulshoppingmall.domain.mall.exception.OpenApiException;
import com.example.seoulshoppingmall.domain.auth.Exception.DuplicateEmailException;
import com.example.seoulshoppingmall.domain.auth.Exception.EmailNotFoundException;
import com.example.seoulshoppingmall.domain.auth.Exception.InvalidPasswordException;
import com.example.seoulshoppingmall.domain.auth.Exception.InvalidPasswordFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
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
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    //이메일 중복 에러(회원가입)
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateEmailException(DuplicateEmailException e) {
        log.error("DuplicateEmailException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(HttpStatus.CONFLICT, e.getMessage()));
    }

    //비밀번호 형식 미충족(회원가입)
    @ExceptionHandler(InvalidPasswordFormatException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidPasswordFormatException(InvalidPasswordFormatException e) {
        log.error("InvalidPasswordFormatException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    //비밀번호 불일치(로그인)
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidPasswordException(InvalidPasswordException e) {
        log.error("InvalidPasswordException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(HttpStatus.UNAUTHORIZED, e.getMessage()));
    }

    //이메일 없음 에러(로그인)
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailNotFoundException(EmailNotFoundException e) {
        log.error("EmailNotFoundException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    // 기타 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception e) {
        log.error("Exception: ", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다."));
    }

    // 예상하지 못한 예외가 발생한 경우에도 사용자에게 500 에러로 응답 -> 메시지로 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "서버 오류가 발생했습니다."));
    }
}