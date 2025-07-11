package com.example.seoulshoppingmall.domain.mall.controller;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.domain.mall.dto.request.MallCursorRequestDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallCursorResponseDto;
import com.example.seoulshoppingmall.domain.mall.service.MallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallGetListResponseDto;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
    ) {
        MallCursorRequestDto requestDto = new MallCursorRequestDto(
                cursorMonitoringDate,
                cursorId,
                size,
                overallRating,
                businessStatus
        );


        MallCursorResponseDto responseDto = mallService.getMallList(requestDto);

        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "성공", responseDto));
    }

    @GetMapping("/v2/malls")
    public MallGetListResponseDto GetPagingListApi(
            @RequestParam(required = false) Integer overallRating,
            @RequestParam(required = false) String businessStatus
    ) {
       return mallService.getMallListFilterService(overallRating, businessStatus);
    }
}
