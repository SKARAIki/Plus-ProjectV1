package com.example.seoulshoppingmall.domain.mall.controller;

import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallCreateResponse;
import com.example.seoulshoppingmall.domain.mall.service.MallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MallController {
    private final MallService mallService;

    public MallController(MallService mallService) {
        this.mallService = mallService;
    }

    // mall 데이터 (세고)저장
    @PostMapping("/collection-openapi")
    public ResponseEntity<MallCreateResponse> createMalls(@RequestBody List<MallOpenApiDto> mallopenapiDtos) {
        int savedCount = mallService.saveAllMalls(mallopenapiDtos);

        MallCreateResponse response = new MallCreateResponse(
                200,
                "OpenAPI에서 " + savedCount + "개의 데이터 수집 완료되었습니다"
        );

        return ResponseEntity.ok(response);
    }
}
