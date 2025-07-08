package com.example.seoulshoppingmall.domain.mall.entity;

public enum BusinessStatus {
    /**
     * Enum 은 대문자표기, 스네이크케이스가 표준
     */
    PROMOTIONAL("PROMOTIONAL","광고(홍보용)"),
    INFO_MISMATCH("INFO_MISMATCH", "등록정보불일치"),
    OPERATION_SUSPENDED("OPERATION_SUSPENDED", "사이트운영중단"),
    ACTIVE("ACTIVE", "영업중"),
    UNKNOWN("UNKNOWN", "확인안됨"),
    TEMPORARILY_CLOSED("TEMPORARILY_CLOSED", "휴업중")
    ;

    private final String businessStatus; // 업소상태
    private final String description; // 업소상태 설명

    BusinessStatus(String businessStatus, String description) {
        this.businessStatus = businessStatus;
        this.description = description;
    }

    /**
     *
     * @param str
     * @return
     */
    public static BusinessStatus fromString(String str) {
        for (BusinessStatus p : values()) {
            if (p.businessStatus.equalsIgnoreCase(str)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid progress: " + str);
    }
}
