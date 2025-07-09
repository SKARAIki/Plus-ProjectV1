package com.example.seoulshoppingmall.domain.mall.dto.openapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MallOpenApiDto  {
    // 속성
        private String businessName;
        private String mallName;
        private String domainName;
        private String phoneNumber;
        private String operatorEmail;
        private String salesNumber;
        private String businessType;
        private String initialReportDate;
        private String companyAddress;
        private String businessStatus;
        private int overallRating;
        private int businessInfoRating;
        private int cancellationPolicyRating;
        private int paymentMethodRating;
        private int termsRating;
        private int privacySecurityRating;
        private String mainProducts;
        private String cancellationPolicyAvailable;
        private String mandatoryDisplayItems;
        private String paymentMethods;
        private String termsCompliance;
        private String privacyPolicy;
        private String additionalPrivacyRequirements;
        private String purchaseSafetyService;
        private String secureServerInstallation;
        private String certificationMark;
        private String deliveryDateDisplay;
        private String returnShippingCostResponsibility;
        private String customerComplaintBoard;
        private String memberWithdrawalMethod;
        private String siteOpeningYear;
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
