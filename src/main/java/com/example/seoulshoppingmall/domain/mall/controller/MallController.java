package com.example.seoulshoppingmall.domain.mall.controller;

import com.example.seoulshoppingmall.domain.mall.dto.response.MallGetListResponseDto;
import com.example.seoulshoppingmall.domain.mall.service.MallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MallController {

    private final MallService mallService;



    @GetMapping("/v3/malls")
    public MallGetListResponseDto GetPagingListApi(
            @RequestParam(required = false) Integer overallRating,
            @RequestParam(required = false) String businessStatus
    ) {
       return mallService.getMallListFilterService(overallRating, businessStatus);
    }
}
