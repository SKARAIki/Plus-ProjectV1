package com.example.seoulshoppingmall.domain.mall.dto.openapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MallOpenApiResponse {

    // XML에서 <row> 태그들이 여러 개 올 것으로 예상됨
    @JsonProperty("row")
    private List<MallOpenApiDto> rows;

    public List<MallOpenApiDto> getRows() {
        return rows;
    }

    public void setRows(List<MallOpenApiDto> rows) {
        this.rows = rows;
    }
}
