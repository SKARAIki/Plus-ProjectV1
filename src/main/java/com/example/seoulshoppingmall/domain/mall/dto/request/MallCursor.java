package com.example.seoulshoppingmall.domain.mall.dto.request;

public class MallCursor {

    private final String monitoringDate;
    private final Long id;

    public static final MallCursor EMPTY = new MallCursor("", 0L);

    public MallCursor(String monitoringDate, Long id) {
        this.monitoringDate = monitoringDate;
        this.id = id;
    }


    public String getMonitoringDate() {
        return monitoringDate;
    }

    public Long getId() {
        return id;
    }
}
