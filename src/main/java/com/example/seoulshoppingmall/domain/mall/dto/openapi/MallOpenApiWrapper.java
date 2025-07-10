package com.example.seoulshoppingmall.domain.mall.dto.openapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MallOpenApiWrapper {

    @JsonProperty("ServiceInternetShopInfo")
    private ServiceInternetShopInfo serviceInternetShopInfo;

    public ServiceInternetShopInfo getServiceInternetShopInfo() {
        return serviceInternetShopInfo;
    }

    public void setServiceInternetShopInfo(ServiceInternetShopInfo serviceInternetShopInfo) {
        this.serviceInternetShopInfo = serviceInternetShopInfo;
    }
}