package com.example.seoulshoppingmall.domain.mall.controller;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallResponseDto;
import com.example.seoulshoppingmall.domain.mall.service.MallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MallController {

    private final MallService mallService;

    @GetMapping("/v3/malls")
    public ResponseEntity<ApiResponse<List<MallResponseDto>>> GetMallList() {
    List<MallResponseDto> mallList = mallService.GetMallList();

        ApiResponse<List<MallResponseDto>> response = ApiResponse.success(
                HttpStatus.OK,
                "성공",
                mallList
        );
        return ResponseEntity.ok(response);
    }
}
