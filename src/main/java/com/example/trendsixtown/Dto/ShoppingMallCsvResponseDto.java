package com.example.trendsixtown.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShoppingMallCsvResponseDto {

    private Long Id;
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

}
