package com.example.seoulshoppingmall.domain.mall.controller;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.domain.mall.dto.request.MallCursorRequestDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallCursorResponseDto;
import com.example.seoulshoppingmall.domain.mall.exception.InvalidQueryParameterException;
import com.example.seoulshoppingmall.domain.mall.service.MallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MallController {

    private final MallService mallService;

    public MallController(MallService mallService) {
        this.mallService = mallService;
    }

    @GetMapping("/v3/malls")
    public ResponseEntity<ApiResponse<MallCursorResponseDto>> GetMallList(
            @RequestParam(required = false) String cursorMonitoringDate,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer overallRating,
            @RequestParam(required = false) String businessStatus
    ) {//유효성 검사
        Optional.ofNullable(overallRating)
                .filter(rating -> rating >= 0 && rating <= 3)
                .orElseThrow(() -> new InvalidQueryParameterException("유효하지 않은 쿼리 파라미터입니다."));

        MallCursorRequestDto requestDto = new MallCursorRequestDto();
        requestDto.setMonitoringDateCursor(cursorMonitoringDate);
        requestDto.setIdCursor(cursorId);
        requestDto.setSize(size);
        requestDto.setOverallRating(overallRating);
        requestDto.setBusinessStatus(businessStatus);

        MallCursorResponseDto responseDto = mallService.GetMallList(requestDto);

        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "성공", responseDto));
    }
}
