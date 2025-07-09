package com.example.seoulshoppingmall.domain.mall.dto.response;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import lombok.Getter;


public class MallResponseDto {

    private Long id;
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

    public MallResponseDto(Mall mall) {
        this.id = mall.getId();
        this.businessName = mall.getBusinessName();
        this.mallName = mall.getMallName();
        this.domainName = mall.getDomainName();
        this.phoneNumber = mall.getPhoneNumber();
        this.operatorEmail = mall.getOperatorEmail();
        this.salesNumber = mall.getSalesNumber();
        this.businessType = mall.getBusinessType();
        this.initialReportDate = mall.getInitialReportDate();
        this.companyAddress = mall.getCompanyAddress();
        this.businessStatus = mall.getBusinessStatus();
        this.overallRating = mall.getOverallRating();
        this.businessInfoRating = mall.getBusinessInfoRating();
        this.cancellationPolicyRating = mall.getCancellationPolicyRating();
        this.paymentMethodRating = mall.getPaymentMethodRating();
        this.termsRating = mall.getTermsRating();
        this.privacySecurityRating = mall.getPrivacySecurityRating();
        this.mainProducts = mall.getMainProducts();
        this.cancellationPolicyAvailable = mall.getCancellationPolicyAvailable();
        this.mandatoryDisplayItems = mall.getMandatoryDisplayItems();
        this.paymentMethods = mall.getPaymentMethods();
        this.termsCompliance = mall.getTermsCompliance();
        this.privacyPolicy = mall.getPrivacyPolicy();
        this.additionalPrivacyRequirements = mall.getAdditionalPrivacyRequirements();
        this.purchaseSafetyService = mall.getPurchaseSafetyService();
        this.secureServerInstallation = mall.getSecureServerInstallation();
        this.certificationMark = mall.getCertificationMark();
        this.deliveryDateDisplay = mall.getDeliveryDateDisplay();
        this.returnShippingCostResponsibility = mall.getReturnShippingCostResponsibility();
        this.customerComplaintBoard = mall.getCustomerComplaintBoard();
        this.memberWithdrawalMethod = mall.getMemberWithdrawalMethod();
        this.siteOpeningYear = mall.getSiteOpeningYear();
        this.monitoringDate = mall.getMonitoringDate();
    }
    //게터
    public Long getId() {
        return id;
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
