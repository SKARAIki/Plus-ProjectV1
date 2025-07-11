package com.example.trendsixtown.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Shopping_Mall")
public class ShoppingMall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String businessName;
    private String mallName;
    @Column(length = 1000)
    private String domainName;
    private String phoneNumber;
    private String operatorEmail;
    private String salesNumber;
    private String businessType;
    private String initialReportDate;
    @Column(length = 512)
    private String companyAddress;
    private String businessStatus;
    private String overallRating;
    private String businessInfoRating;
    private String cancellationPolicyRating;
    private String paymentMethodRating;
    private String termsRating;
    private String privacySecurityRating;
    private String mainProducts;
    private String cancellationPolicyAvailable;
    private String mandatoryDisplayItems;
    private String paymentMethods;
    private String termsCompliance;
    private String privacyPolicy;
    //@Column(columnDefinition = "tinyint default 0")
    private boolean additionalPrivacyRequirements;
    private String purchaseSafetyService;
    private String secureServerInstallation;
    private String certificationMark;
    private String deliveryDateDisplay;
    private String returnShippingCostResponsibility;
    private String customerComplaintBoard;
    private String memberWithdrawalMethod;
    private String siteOpeningYear;
    private String monitoringDate;

    public ShoppingMall(String additionalPrivacyRequirements, String businessInfoRating,
                        String businessName, String businessStatus, String businessType,
                        String cancellationPolicyAvailable, String cancellationPolicyRating,
                        String certificationMark, String companyAddress, String customerComplaintBoard,
                        String deliveryDateDisplay, String domainName, String initialReportDate,
                        String mainProducts, String mallName, String mandatoryDisplayItems,
                        String memberWithdrawalMethod, String monitoringDate, String operatorEmail
            , String overallRating, String paymentMethodRating, String paymentMethods,
                        String phoneNumber, String privacyPolicy, String privacySecurityRating
            , String purchaseSafetyService, String returnShippingCostResponsibility,
                        String salesNumber, String secureServerInstallation,
                        String siteOpeningYear, String termsCompliance, String termsRating) {

        this.businessName = businessName;
        this.mallName = mallName;
        this.domainName = domainName;
        this.phoneNumber = phoneNumber;
        this.operatorEmail = operatorEmail;
        this.salesNumber = salesNumber;
        this.businessName = businessName;
        this.businessType = businessType;
        this.initialReportDate = initialReportDate;
        this.companyAddress = companyAddress;
        this.businessStatus = businessStatus;
        this.overallRating = overallRating;
        this.businessInfoRating = businessInfoRating;
        this.cancellationPolicyRating = cancellationPolicyRating;
        this.paymentMethodRating = paymentMethodRating;
        this.termsRating = termsRating;
        this.privacySecurityRating = privacySecurityRating;
        this.mainProducts = mainProducts;
        this.cancellationPolicyAvailable = cancellationPolicyAvailable;
        this.mandatoryDisplayItems = mandatoryDisplayItems;
        this.paymentMethods = paymentMethods;
        this.termsCompliance = termsCompliance;
        this.privacyPolicy = privacyPolicy;
        //1일경우 true / 나머지는 false
        this.additionalPrivacyRequirements = "1".equals(additionalPrivacyRequirements);
        this.purchaseSafetyService = purchaseSafetyService;
        this.secureServerInstallation = secureServerInstallation;
        this.certificationMark = certificationMark;
        this.deliveryDateDisplay = deliveryDateDisplay;
        this.returnShippingCostResponsibility = returnShippingCostResponsibility;
        this.customerComplaintBoard = customerComplaintBoard;
        this.memberWithdrawalMethod = memberWithdrawalMethod;
        this.siteOpeningYear = siteOpeningYear;
        this.monitoringDate = monitoringDate;


    }
}
