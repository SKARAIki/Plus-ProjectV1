package com.example.seoulshoppingmall.domain.mall.dto.response;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;


public class MallGetListV1Response {
    private final Long id;
    private final String businessName;
    private final String mallName;
    private final String domainName;
    private final String phoneName;
    private final String operatorEmail;
    private final String salesNumber;
    private final String businessType;
    private final String initialReportDate;
    private final String companyAddress;
    private final String businessStatus;
    private final int overallRating;
    private final int businessInfoRating;
    private final int cancellationPolicyRating;
    private final int paymentMethodRating;
    private final int termsRating;
    private final int privacySecurityRating;
    private final String mainProducts;
    private final String cancellationPolicyAvailable;
    private final String mandatoryDisplayItems;
    private final String paymentMethods;
    private final String termsCompliance;
    private final String privacyPolicy;
    private final String additionalPrivacyRequirements;
    private final String purchaseSafetyService;
    private final String secureServerInstallation;
    private final String certificationMark;
    private final String deliveryDateDisplay;
    private final String returnShippingCostResponsibility;
    private final String customerComplaintBoard;
    private final String memberWithdrawalMethod;
    private final String siteOpeningYear;
    private final String monitoringDate;

    public MallGetListV1Response(Mall mall) {
        this.id = mall.getId();
        this.businessName = mall.getBusinessName();
        this.mallName = mall.getMallName();
        this.domainName = mall.getDomainName();
        this.phoneName = mall.getPhoneNumber();
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

    public String getPhoneName() {
        return phoneName;
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
