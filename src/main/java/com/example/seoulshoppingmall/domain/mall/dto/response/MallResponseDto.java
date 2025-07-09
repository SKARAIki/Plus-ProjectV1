package com.example.seoulshoppingmall.domain.mall.dto.response;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;

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
}
