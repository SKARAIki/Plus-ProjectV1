package com.example.seoulshoppingmall.domain.mall.dto.openapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// JsonIgnoreProperties : json으로 파싱시에 없는 필드는 무시
@JsonIgnoreProperties(ignoreUnknown = true)
public class MallOpenApiDto {
    // 속성
    // 기존 entity호출이 아닌 불러오는것이기 때문에 이름을 바꿔줘야함(홍태호 튜터님)
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

    // 기본 생성자
    public MallOpenApiDto() {
    }

    // Getter Setter
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOperatorEmail() {
        return operatorEmail;
    }

    public void setOperatorEmail(String operatorEmail) {
        this.operatorEmail = operatorEmail;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getInitialReportDate() {
        return initialReportDate;
    }

    public void setInitialReportDate(String initialReportDate) {
        this.initialReportDate = initialReportDate;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public int getBusinessInfoRating() {
        return businessInfoRating;
    }

    public void setBusinessInfoRating(int businessInfoRating) {
        this.businessInfoRating = businessInfoRating;
    }

    public int getCancellationPolicyRating() {
        return cancellationPolicyRating;
    }

    public void setCancellationPolicyRating(int cancellationPolicyRating) {
        this.cancellationPolicyRating = cancellationPolicyRating;
    }

    public int getPaymentMethodRating() {
        return paymentMethodRating;
    }

    public void setPaymentMethodRating(int paymentMethodRating) {
        this.paymentMethodRating = paymentMethodRating;
    }

    public int getTermsRating() {
        return termsRating;
    }

    public void setTermsRating(int termsRating) {
        this.termsRating = termsRating;
    }

    public int getPrivacySecurityRating() {
        return privacySecurityRating;
    }

    public void setPrivacySecurityRating(int privacySecurityRating) {
        this.privacySecurityRating = privacySecurityRating;
    }

    public String getMainProducts() {
        return mainProducts;
    }

    public void setMainProducts(String mainProducts) {
        this.mainProducts = mainProducts;
    }

    public String getCancellationPolicyAvailable() {
        return cancellationPolicyAvailable;
    }

    public void setCancellationPolicyAvailable(String cancellationPolicyAvailable) {
        this.cancellationPolicyAvailable = cancellationPolicyAvailable;
    }

    public String getMandatoryDisplayItems() {
        return mandatoryDisplayItems;
    }

    public void setMandatoryDisplayItems(String mandatoryDisplayItems) {
        this.mandatoryDisplayItems = mandatoryDisplayItems;
    }

    public String getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public String getTermsCompliance() {
        return termsCompliance;
    }

    public void setTermsCompliance(String termsCompliance) {
        this.termsCompliance = termsCompliance;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public String getAdditionalPrivacyRequirements() {
        return additionalPrivacyRequirements;
    }

    public void setAdditionalPrivacyRequirements(String additionalPrivacyRequirements) {
        this.additionalPrivacyRequirements = additionalPrivacyRequirements;
    }

    public String getPurchaseSafetyService() {
        return purchaseSafetyService;
    }

    public void setPurchaseSafetyService(String purchaseSafetyService) {
        this.purchaseSafetyService = purchaseSafetyService;
    }

    public String getSecureServerInstallation() {
        return secureServerInstallation;
    }

    public void setSecureServerInstallation(String secureServerInstallation) {
        this.secureServerInstallation = secureServerInstallation;
    }

    public String getCertificationMark() {
        return certificationMark;
    }

    public void setCertificationMark(String certificationMark) {
        this.certificationMark = certificationMark;
    }

    public String getDeliveryDateDisplay() {
        return deliveryDateDisplay;
    }

    public void setDeliveryDateDisplay(String deliveryDateDisplay) {
        this.deliveryDateDisplay = deliveryDateDisplay;
    }

    public String getReturnShippingCostResponsibility() {
        return returnShippingCostResponsibility;
    }

    public void setReturnShippingCostResponsibility(String returnShippingCostResponsibility) {
        this.returnShippingCostResponsibility = returnShippingCostResponsibility;
    }

    public String getCustomerComplaintBoard() {
        return customerComplaintBoard;
    }

    public void setCustomerComplaintBoard(String customerComplaintBoard) {
        this.customerComplaintBoard = customerComplaintBoard;
    }

    public String getMemberWithdrawalMethod() {
        return memberWithdrawalMethod;
    }

    public void setMemberWithdrawalMethod(String memberWithdrawalMethod) {
        this.memberWithdrawalMethod = memberWithdrawalMethod;
    }

    public String getSiteOpeningYear() {
        return siteOpeningYear;
    }

    public void setSiteOpeningYear(String siteOpeningYear) {
        this.siteOpeningYear = siteOpeningYear;
    }

    public String getMonitoringDate() {
        return monitoringDate;
    }

    public void setMonitoringDate(String monitoringDate) {
        this.monitoringDate = monitoringDate;
    }
}
