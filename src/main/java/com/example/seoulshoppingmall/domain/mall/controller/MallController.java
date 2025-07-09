package com.example.seoulshoppingmall.domain.mall.controller;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallGetListV1Response;
import com.example.seoulshoppingmall.domain.mall.service.MallService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MallController {
    private final MallService mallService;

    public MallController(MallService mallService) {
        this.mallService = mallService;
    }

    /**
     *
     * @param overallRating 0~3 입력 null 시 예외포인트 발생
     * @param businessStatus 사이트운영중단, 휴업중, 광고용(홍보용), 등록정보불일치, 사이트폐쇄, 영업중, 확인안됨
     * @return
     */
    @GetMapping("/v1/malls")
    public ApiResponse<List<MallGetListV1Response>> getListMallsInfoApi(@Valid @RequestParam(required = false) Integer overallRating,
                                                                        @Valid @RequestParam(required = false) Collection<String> businessStatus
    ) {
        List<MallGetListV1Response> listMallsInfoProcess = mallService.getListMallsInfoProcess(overallRating, businessStatus);
        ApiResponse<List<MallGetListV1Response>> success = ApiResponse.success(HttpStatus.OK, "성공", listMallsInfoProcess);
        return success;
    }
}
