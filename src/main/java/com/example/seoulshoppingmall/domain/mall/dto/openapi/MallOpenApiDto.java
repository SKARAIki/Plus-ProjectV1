package com.example.seoulshoppingmall.domain.mall.dto.openapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MallOpenApiDto {
    // 기존 entity호출이 아닌 불러오는것이기 때문에 이름을 바꿔줘야함
    // @JsonProperty : json데이터와 자바 객체간의 매핑할 필드명을 지정할 때 사용
    @JsonProperty("COMPANY")
    private String businessName;

    @JsonProperty("SHOP_NAME")
    private String mallName;

    @JsonProperty("DOMAIN_NAME")
    private String domainName;

    @JsonProperty("TEL")
    private String phoneNumber;

    @JsonProperty("EMAIL")
    private String operatorEmail;

    @JsonProperty("UPJONG_NBR")
    private String salesNumber;

    @JsonProperty("YPFORM")
    private String businessType;

    @JsonProperty("FIRST_HEO_DATE")
    private String initialReportDate;

    @JsonProperty("COM_ADDR")
    private String companyAddress;

    @JsonProperty("STAT_NM")
    private String businessStatus;

    @JsonProperty("TOT_RATINGPOINT")
    private int overallRating;

    @JsonProperty("CHOGI_RATINGPOINT")
    private int businessInfoRating;

    @JsonProperty("CHUNG_RATINGPOINT")
    private int cancellationPolicyRating;

    @JsonProperty("DEAL_RATINGPOINT")
    private int paymentMethodRating;

    @JsonProperty("PYOJUN_RATINGPOINT")
    private int termsRating;

    @JsonProperty("SECURITY_RATINGPOINT")
    private int privacySecurityRating;

    @JsonProperty("SERVICE")
    private String mainProducts;

    @JsonProperty("CHUNG")
    private String cancellationPolicyAvailable;

    @JsonProperty("CHOGI")
    private String mandatoryDisplayItems;

    @JsonProperty("GYULJE")
    private String paymentMethods;

    @JsonProperty("PYOJUN")
    private String termsCompliance;

    @JsonProperty("P_INFO_CARE")
    private String privacyPolicy;

    @JsonProperty("PER_INFO")
    private String additionalPrivacyRequirements;

    @JsonProperty("DEAL_CARE")
    private String purchaseSafetyService;

    @JsonProperty("SSL_YN")
    private String secureServerInstallation;

    @JsonProperty("INJEUNG")
    private String certificationMark;

    @JsonProperty("BAESONG_YEJEONG")
    private String deliveryDateDisplay;

    @JsonProperty("BAESONG")
    private String returnShippingCostResponsibility;

    @JsonProperty("CLIENT_BBS")
    private String customerComplaintBoard;

    @JsonProperty("LEAVE")
    private String memberWithdrawalMethod;

    @JsonProperty("KAESOL_YEAR")
    private String siteOpeningYear;

    @JsonProperty("REG_DATE")
    private String monitoringDate;

    public MallOpenApiDto() {
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getMallName() {
        return mallName;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOperatorEmail() {
        return operatorEmail;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public String getBusinessType() {
        return businessType;
    }

    public String getInitialReportDate() {
        return initialReportDate;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public int getBusinessInfoRating() {
        return businessInfoRating;
    }

    public int getCancellationPolicyRating() {
        return cancellationPolicyRating;
    }

    public int getPaymentMethodRating() {
        return paymentMethodRating;
    }

    public int getTermsRating() {
        return termsRating;
    }

    public int getPrivacySecurityRating() {
        return privacySecurityRating;
    }

    public String getMainProducts() {
        return mainProducts;
    }

    public String getCancellationPolicyAvailable() {
        return cancellationPolicyAvailable;
    }

    public String getMandatoryDisplayItems() {
        return mandatoryDisplayItems;
    }

    public String getPaymentMethods() {
        return paymentMethods;
    }

    public String getTermsCompliance() {
        return termsCompliance;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public String getAdditionalPrivacyRequirements() {
        return additionalPrivacyRequirements;
    }

    public String getPurchaseSafetyService() {
        return purchaseSafetyService;
    }

    public String getSecureServerInstallation() {
        return secureServerInstallation;
    }

    public String getCertificationMark() {
        return certificationMark;
    }

    public String getDeliveryDateDisplay() {
        return deliveryDateDisplay;
    }

    public String getReturnShippingCostResponsibility() {
        return returnShippingCostResponsibility;
    }

    public String getCustomerComplaintBoard() {
        return customerComplaintBoard;
    }

    public String getMemberWithdrawalMethod() {
        return memberWithdrawalMethod;
    }

    public String getSiteOpeningYear() {
        return siteOpeningYear;
    }

    public String getMonitoringDate() {
        return monitoringDate;
    }
}
