package com.example.seoulshoppingmall.domain.mall.dto.request;

public class MallCursorRequestDto {
    //마지막으로 조회한 쇼핑몰의 monitoringDate 커서역할
    private String monitoringDateCursor;
    //마지막으로 조회한 쇼핑몰의 ID (동일 날짜일 경우 정렬 보조)
    private Long idCursor;
    //한 페이지에 가져올 데이터 수
    private int size = 10;
    //검색 조건: 평점
    private Integer overallRating;
    //검색조건: 사업 상태
    private String businessStatus;
    //getter추가
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
    //setter 추가
    public void setMonitoringDateCursor(String monitoringDateCursor) {
        this.monitoringDateCursor = monitoringDateCursor;
    }

    public void setIdCursor(Long idCursor) {
        this.idCursor = idCursor;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setOverallRating(Integer overallRating) {
        this.overallRating = overallRating;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }
}
