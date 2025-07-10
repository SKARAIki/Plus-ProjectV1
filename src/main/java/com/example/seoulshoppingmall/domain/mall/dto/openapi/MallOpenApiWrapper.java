package com.example.seoulshoppingmall.domain.mall.dto.openapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MallOpenApiWrapper {
    //ServiceInternetShopInfo 필드"명"을 Java 필드에 매핑시키는 것
    @JsonProperty("ServiceInternetShopInfo")
    private ServiceInternetShopInfo serviceInternetShopInfo;

    public ServiceInternetShopInfo getServiceInternetShopInfo() {
        return serviceInternetShopInfo;
    }

    public void setServiceInternetShopInfo(ServiceInternetShopInfo serviceInternetShopInfo) {
        this.serviceInternetShopInfo = serviceInternetShopInfo;
    }
}
// setter가 필요한 이유) 라이브러리를 통해 데이터를 주입할때는 필요함