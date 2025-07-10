package com.example.seoulshoppingmall.domain.mall.dto.openapi;

import java.util.List;

public class ServiceInternetShopInfo {

    private List<MallOpenApiDto> row;

    public List<MallOpenApiDto> getRow() {
        return row;
    }

    public void setRow(List<MallOpenApiDto> row) {
        this.row = row;
    }
}