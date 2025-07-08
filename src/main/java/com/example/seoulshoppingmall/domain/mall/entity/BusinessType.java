package com.example.seoulshoppingmall.domain.mall.entity;

public enum BusinessType {
    OPEN_MARKET("OPEN_MARKET", "오픈마켓"),
    ONLINE_LEARNING("ONLINE_LEARNING", "온라인학습"),
    GENERAL_MALL("GENERAL_MALL", "일반쇼핑몰"),
    SUBSCRIPTION_INDUCED("SUBSCRIPTION_INDUCED", "청약유인"),
    CONTENT_SERVICE("CONTENT_SERVICE", "컨텐츠,서비스"),
    GLOBAL_AGENCY("GLOBAL_AGENCY", "해외구매대행")
    ;
    private final String businessType;
    private final String description;

    BusinessType(String businessType, String description) {
        this.businessType = businessType;
        this. description = description;
    }
}
