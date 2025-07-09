package com.example.seoulshoppingmall.domain.mall.dto.response;

import java.util.List;

public class MallCursorResponseDto {
    //현재 페이지의 쇼핑몰 리스트
    private List<MallResponseDto> malls;
    //다음 페이지를 위한 커서 정보
    private String nextMonitoringDateCursor;
    private Long nextIdCursor;

    public MallCursorResponseDto(List<MallResponseDto> malls, String nextdate, Long nextId) {
        this.malls = malls;
        this.nextMonitoringDateCursor = nextdate;
        this.nextIdCursor = nextId;
    }

    public List<MallResponseDto> getMalls() {
        return malls;
    }

    public String getNextMonitoringDateCursor() {
        return nextMonitoringDateCursor;
    }

    public Long getNextIdCursor() {
        return nextIdCursor;
    }
}
