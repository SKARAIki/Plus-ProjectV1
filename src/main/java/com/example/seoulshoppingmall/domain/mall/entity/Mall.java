package com.example.seoulshoppingmall.domain.mall.entity;

import com.example.seoulshoppingmall.common.entity.BaseTimeEntity;
import com.example.seoulshoppingmall.domain.auth.entity.Member;
import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "shopping_malls")
public class Mall{

    /**
     * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(name = "business_name")
    private String businessName;


    @Column(name = "mall_name")
    private String mallName;


    @Column(name = "domain_name")
    private String domainName;


    @Column(name = "phone_number")
    private String phoneNumber;


    @Column(name = "operator_email")
    private String operatorEmail;

    @Column(name = "sales_number")
    private String salesNumber;

    @Column(name = "business_type")
    private String businessType;


    @Column(name = "initial_report_date")
    private String initialReportDate;


    @Column(name = "company_address", length = 512)
    private String companyAddress;


    @Column(name = "business_status")
    private String businessStatus;


    @Column(name = "overall_rating")
    private int overallRating;


    @Column(name = "business_info_rating")
    private int businessInfoRating;


    @Column(name = "cancellation_policy_rating")
    private int cancellationPolicyRating;


    @Column(name = "payment_method_rating")
    private int paymentMethodRating;


    @Column(name = "terms_rating")
    private int termsRating;


    @Column(name = "privacy_security_rating")
    private int privacySecurityRating;

    @Column(name = "main_products")
    private String mainProducts;


    @Column(name = "cancellation_policy_available")
    private String cancellationPolicyAvailable;

    @Column(name = "mandatory_display_items")
    private String mandatoryDisplayItems;

    @Column(name = "payment_methods")
    private String paymentMethods;


    @Column(name = "terms_compliance")
    private String termsCompliance;


    @Column(name = "privacy_policy")
    private String privacyPolicy;

    @Column(name = "additional_privacy_requirements")
    private String additionalPrivacyRequirements;


    @Column(name = "purchase_safety_service")
    private String purchaseSafetyService;

    @Column(name = "secure_server_installation")
    private String secureServerInstallation;

    @Column(name = "certification_mark")
    private String certificationMark;


    @Column(name = "delivery_date_display")
    private String deliveryDateDisplay;


    @Column(name = "return_shipping_cost_responsibility")
    private String returnShippingCostResponsibility;


    @Column(name = "customer_complaint_board")
    private String customerComplaintBoard;


    @Column(name = "member_withdrawal_method")
    private String memberWithdrawalMethod;


    @Column(name = "site_opening_year")
    private String siteOpeningYear;


    @Column(name = "monitoring_date")
    private String monitoringDate;


    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    protected Mall() {
    }

    public Mall(Long id, String businessName, String mallName, String domainName, String phoneNumber, String operatorEmail,
                String salesNumber, String businessType, String initialReportDate, String companyAddress, String businessStatus,
                Integer overallRating, Integer businessInfoRating, Integer cancellationPolicyRating, Integer paymentMethodRating,
                Integer termsRating, Integer privacySecurityRating, String mainProducts, String cancellationPolicyAvailable,
                String mandatoryDisplayItems, String paymentMethods, String termsCompliance, String privacyPolicy,
                String additionalPrivacyRequirements, String purchaseSafetyService, String secureServerInstallation,
                String certificationMark, String deliveryDateDisplay, String returnShippingCostResponsibility,
                String customerComplaintBoard, String memberWithdrawalMethod, String siteOpeningYear, String monitoringDate
    ) {
        this.Id = id;
        this.businessName = businessName;
        this.mallName = mallName;
        this.domainName = domainName;
        this.phoneNumber = phoneNumber;
        this.operatorEmail = operatorEmail;
        this.salesNumber = salesNumber;
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
        this.additionalPrivacyRequirements = additionalPrivacyRequirements;
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

    /**
     * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
     */


    /**
     * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
     */


    /**
     * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
     */
    public Long getId() {
        return Id;
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

    public Integer getOverallRating() {
        return overallRating;
    }

    public Integer getBusinessInfoRating() {
        return businessInfoRating;
    }

    public Integer getCancellationPolicyRating() {
        return cancellationPolicyRating;
    }

    public Integer getPaymentMethodRating() {
        return paymentMethodRating;
    }

    public Integer getTermsRating() {
        return termsRating;
    }

    public Integer getPrivacySecurityRating() {
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

    // MallService에서 쓰는 정적 메서드
    public static Mall fromDto(MallOpenApiDto dto) {
        return new Mall(
                null,
                safeString(dto.getBusinessName()),
                safeString(dto.getMallName()),
                safeString(dto.getDomainName()),
                safeString(dto.getPhoneNumber()),
                safeString(dto.getOperatorEmail()),
                safeString(dto.getSalesNumber()),
                safeString(dto.getBusinessType()),
                safeString(dto.getInitialReportDate()),
                safeString(dto.getCompanyAddress()),
                safeString(dto.getBusinessStatus()),
                safeInteger(dto.getOverallRating()),
                safeInteger(dto.getBusinessInfoRating()),
                safeInteger(dto.getCancellationPolicyRating()),
                safeInteger(dto.getPaymentMethodRating()),
                safeInteger(dto.getTermsRating()),
                safeInteger(dto.getPrivacySecurityRating()),
                safeString(dto.getMainProducts()),
                safeString(dto.getCancellationPolicyAvailable()),
                safeString(dto.getMandatoryDisplayItems()),
                safeString(dto.getPaymentMethods()),
                safeString(dto.getTermsCompliance()),
                safeString(dto.getPrivacyPolicy()),
                safeString(dto.getAdditionalPrivacyRequirements()),
                safeString(dto.getPurchaseSafetyService()),
                safeString(dto.getSecureServerInstallation()),
                safeString(dto.getCertificationMark()),
                safeString(dto.getDeliveryDateDisplay()),
                safeString(dto.getReturnShippingCostResponsibility()),
                safeString(dto.getCustomerComplaintBoard()),
                safeString(dto.getMemberWithdrawalMethod()),
                safeString(dto.getSiteOpeningYear()),
                safeString(dto.getMonitoringDate())
        );
    }
    private static String safeString(String value) {
        return value != null ? value : "";
    }

    private static Integer safeInteger(Integer value) {
        return value != null ? value : 0;
    }

}
