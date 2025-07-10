package com.example.seoulshoppingmall.domain.mall.controller;

import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallCreateResponse;
import com.example.seoulshoppingmall.domain.mall.service.MallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/malls")
public class MallController {
    private final MallService mallService;

    // 생성자
    public MallController(MallService mallService) {
        this.mallService = mallService;
    }

    @PostMapping("/collection-openapi/{start}/{end}")
    public ResponseEntity<MallCreateResponse> createMalls(@PathVariable int start, @PathVariable int end) {
        // 서울시 OpenAPI 직접 호출 -> dto로 변환(리스트형태로)
        List<MallOpenApiDto> mallDtos = mallService.fetchAndParseOpenApiData(start, end);
        // db에 "세고" 저장
        int savedCount = mallService.saveAllMalls(mallDtos);
        // 응답
        MallCreateResponse response = new MallCreateResponse(
                200,
                "서울시 OpenAPI에서 " + savedCount + "개 데이터 수집 완료되었습니다"
        );

        return ResponseEntity.ok(response);
    }
}
// mall 데이터 (세고)저장
//    @PostMapping("/collection-openapi")
//    public ResponseEntity<MallCreateResponse> createMalls() {
//        List<MallOpenApiDto> mallOpenApiDto = mallService.fetchAndParseOpenApiData(); // 직접 OpenAPI 요청
//        int savedCount = mallService.saveAllMalls(mallOpenApiDto);
//
//        MallCreateResponse response = new MallCreateResponse(200, "OpenAPI에서 " + savedCount + "개의 데이터 수집 완료되었습니다");
//        return ResponseEntity.ok(response);
//    }