package com.example.seoulshoppingmall.domain.mall.dto.response;

import java.util.List;

public class MallCursorResponseDto {
    private List<MallResponseDto> malls;
    private String nextMonitoringDateCursor;
    private Long nextIdCursor;

    public MallCursorResponseDto(List<MallResponseDto> malls, String nextdate, Long nextId) {
        this.malls = malls;
        this.nextMonitoringDateCursor = nextdate;
        this.nextIdCursor = nextId;
    }
}
