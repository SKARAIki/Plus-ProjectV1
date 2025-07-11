package com.example.seoulshoppingmall.domain.mall.dto.request;

import com.example.seoulshoppingmall.domain.mall.exception.InvalidQueryParameterException;

import java.util.Optional;

public class MallCursorRequestDto {
    //마지막으로 조회한 쇼핑몰의 monitoringDate 커서역할
    private final String monitoringDateCursor;
    //마지막으로 조회한 쇼핑몰의 ID (동일 날짜일 경우 정렬 보조)
    private final Long idCursor;
    //한 페이지에 가져올 데이터 수
    private final int size;
    //검색 조건: 평점
    private final Integer overallRating;
    //검색조건: 사업 상태
    private final String businessStatus;

    public MallCursorRequestDto(
            String monitoringDateCursor, Long idCursor, int size, Integer overallRating, String businessStatus
    ) {
        Optional.ofNullable(overallRating)
                .filter(rating-> rating >= 0 && rating <=3)
                .orElseThrow(() -> new InvalidQueryParameterException("유효하지 않은 쿼리 파라미터입니다."));
        this.monitoringDateCursor = monitoringDateCursor;
        this.idCursor = idCursor;
        this.size = size;
        this.overallRating = overallRating;
        this.businessStatus = businessStatus;
    }
    //getter 추가
    public String getMonitoringDateCursor() {
        return monitoringDateCursor;
    }

    public Long getIdCursor() {
        return idCursor;
    }

    public int getSize() {
        return size;
    }

    public Integer getOverallRating() {
        return overallRating;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

}
