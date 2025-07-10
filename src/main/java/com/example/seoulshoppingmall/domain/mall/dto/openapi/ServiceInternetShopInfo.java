package com.example.seoulshoppingmall.domain.mall.dto.openapi;

import java.util.List;

public class ServiceInternetShopInfo {

    // row 배열을 리스트로 매핑
    private List<MallOpenApiDto> row;

    // row목록 읽을때
    public List<MallOpenApiDto> getRow() {
        return row;
    }

    // setter : row 값을 주입
    public void setRow(List<MallOpenApiDto> row) {
        this.row = row;
    }
}