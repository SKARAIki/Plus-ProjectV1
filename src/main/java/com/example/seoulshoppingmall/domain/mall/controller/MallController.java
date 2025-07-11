package com.example.seoulshoppingmall.domain.mall.controller;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallGetListV1Response;
import jakarta.validation.Valid;
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
import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallCreateResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MallController {
    private final MallService mallService;

    public MallController(MallService mallService) {
        this.mallService = mallService;
    }

    /**
     * @param overallRating  0~3 입력 null 시 예외포인트 발생
     * @param businessStatus 사이트운영중단, 휴업중, 광고용(홍보용), 등록정보불일치, 사이트폐쇄, 영업중, 확인안됨
     * @return
     */
    @GetMapping("/v1/malls")
    public ApiResponse<List<MallGetListV1Response>> getListMallsInfoApi(
            @Valid @RequestParam(required = false) Integer overallRating,
            @Valid @RequestParam(required = false) String businessStatus
    ) {

        List<MallGetListV1Response> listMallsInfoProcess
                = mallService.getListMallsInfoProcess(overallRating, businessStatus);
        ApiResponse<List<MallGetListV1Response>> success
                = ApiResponse.success(HttpStatus.OK, "성공", listMallsInfoProcess);
        return success;
    }

    @GetMapping("/v2/malls")
    public MallGetListResponseDto GetPagingListApi(
            @RequestParam(required = false) Integer overallRating,
            @RequestParam(required = false) String businessStatus
    ) {
        return mallService.getMallListFilterService(overallRating, businessStatus);
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