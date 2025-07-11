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

    // keyword가 있을경우 필터링된 데이터만 저장, 없으면 전체저장
    @GetMapping("/collection-openapi")
    public ResponseEntity<MallCreateResponse> fetchAndSaveMalls(@RequestParam int start, @RequestParam int end, @RequestParam (required = false) String keyword) {
        // 서울시 OpenAPI 직접 호출 -> dto로 변환(리스트형태로)
        List<MallOpenApiDto> allMallOpenapiDto = mallService.fetchAndParseOpenApiData(start, end);
        List<MallOpenApiDto> filteredDtos = mallService.filterByKeyword(allMallOpenapiDto, keyword);
        int savedCount = mallService.saveAllMalls(filteredDtos);
        // 응답
        // keyword가 있을경우, 없을경우 전체저장 메세지 분리
        String message;
        if (keyword != null && !keyword.isBlank()) {
            message = String.format(
                    "서울시 OpenAPI에서 총 %d개의 데이터를 수집했고, 이 중 ‘%s’ 키워드가 포함된 %d개를 저장했습니다.",
                    allMallOpenapiDto.size(), keyword, savedCount
            );
        } else {
            message = String.format("서울시 OpenAPI에서 %d개의 데이터를 저장했습니다.", savedCount);
        }

        MallCreateResponse response = new MallCreateResponse(200,message);
        return ResponseEntity.ok(response);
    }
}